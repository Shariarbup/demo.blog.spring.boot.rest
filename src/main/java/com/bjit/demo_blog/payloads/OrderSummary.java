package com.bjit.demo_blog.payloads;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderSummary {
    private String customerName;
    private Long orderCount;
}
