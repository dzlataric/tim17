package com.payment.seller1.notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface SimpleMessageRepository extends JpaRepository<SimpleMessage, Long> {

	@PostFilter("hasRole('ROLE_ANONYMOUS')")
	List<SimpleMessage> findAll();

	@PostAuthorize("hasPermission(returnObject, 'READ')")
	SimpleMessage findById(Integer id);

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasPermission(#simpleMessage, 'WRITE')")
	SimpleMessage save(@Param("noticeMessage") SimpleMessage simpleMessage);

}
