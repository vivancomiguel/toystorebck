package com.toystore.entity;

import java.util.Calendar;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "delivery")
public class Delivery {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "delivery_id")
	private Long deliveryId;
	
	@Column(name = "created_at", insertable=false,  updatable=false , columnDefinition = "timestamp default CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order_id;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product_id;
}
