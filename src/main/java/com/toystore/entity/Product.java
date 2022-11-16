package com.toystore.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "product_name")
	private String name;
	@Column(name = "price")
	private Double price;
	
}
