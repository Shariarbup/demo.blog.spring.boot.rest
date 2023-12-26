package com.bjit.demo_blog.serviceImpl;

import com.bjit.demo_blog.config.AppConstants;
import com.bjit.demo_blog.entity.Role;
import com.bjit.demo_blog.entity.User;
import com.bjit.demo_blog.exceptions.ResourceNotFoundException;
import com.bjit.demo_blog.payloads.UserDto;
import com.bjit.demo_blog.repositories.RoleRepository;
import com.bjit.demo_blog.repositories.UserRepository;
import com.bjit.demo_blog.services.UserService;
import com.bjit.demo_blog.utils.ExcelHelper;
import com.bjit.demo_blog.utils.SearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        //encoded the passsword
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //role
        Role role = this.roleRepository.findById(AppConstants.ROLE_NORMAL).get();
        user.getRoles().add(role);
        User newUser = this.userRepository.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToUser(userDto);
        User saveUser = userRepository.save(user);
        return userToDto(saveUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User userUpdate = userRepository.save(user);
        return userToDto(userUpdate);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "id", userId));
        userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    @Override
    public void saveUserFromImportExcel(MultipartFile file) {
        try {
            List<User> users = ExcelHelper.convertExcelToListOfUser((InputStream) file.getInputStream());
            this.userRepository.saveAll(users);
        } catch (Exception e) {

        }
    }

    @Override
    public String exportUserListPdf(String reportFormat) throws FileNotFoundException, JRException {
        String path = "E:\\";
        List<User> users = userRepository.findAll();
        File file = ResourceUtils.getFile("classpath:user-list.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> params = new HashMap<>();
        params.put("createdBy", "Al Shariar");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, jrBeanCollectionDataSource);
        if(reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "user-list.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "user-list.pdf");
        }
        return "Report generated successfully to this path : "+ path;
    }

    @Override
    public List<User> findAllBySimpleQuery(Long id, String name, String email ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        // select * from user
        Root<User> root = userCriteriaQuery.from(User.class);

        //prepare WHERE clause -> means prepare predicate

        Predicate idPredicate = criteriaBuilder.equal(root.get("id"), id);

        Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");

        Predicate emailPredicate = criteriaBuilder.like(root.get("email"), "%" + email + "%");

        Predicate orPredicate = criteriaBuilder.or(idPredicate, namePredicate, emailPredicate);

        Predicate andPredicate = criteriaBuilder.and(idPredicate, namePredicate, emailPredicate);

        //output
        userCriteriaQuery.where(andPredicate);

        TypedQuery<User> query = entityManager.createQuery(userCriteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<User> findAllByAdvancedQuery(SearchRequest searchRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userCriteriaQuery = criteriaBuilder.createQuery(User.class);

        // select * from user
        Root<User> root = userCriteriaQuery.from(User.class);
        List<Predicate> predicates = new ArrayList<>();

        //prepare WHERE clause -> means prepare predicate
        if (searchRequest.getId() != null) {
            Predicate idPredicate = criteriaBuilder.equal(root.get("id"), searchRequest.getId());
            predicates.add(idPredicate);
        }

        if (searchRequest.getName() != null) {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + searchRequest.getName() + "%");
            predicates.add(namePredicate);
        }

        if (searchRequest.getEmail() != null) {
            Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), searchRequest.getEmail());
            predicates.add(emailPredicate);
        }


        Predicate orPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));

        Predicate andPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        //output
        userCriteriaQuery.where(andPredicate);

        TypedQuery<User> query = entityManager.createQuery(userCriteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<UserDto> getAllUsersWithPagination(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtos = userList.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return  userDtos;
    }


    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
