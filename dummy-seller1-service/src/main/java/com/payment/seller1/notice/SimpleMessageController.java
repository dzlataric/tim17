package com.payment.seller1.notice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SimpleMessageController {

	private final SimpleMessageRepository simpleMessageRepository;

	@Autowired
	public SimpleMessageController(final SimpleMessageRepository simpleMessageRepository) {
		this.simpleMessageRepository = simpleMessageRepository;
	}

	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public List<SimpleMessage> getMessages() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info(authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ANOTHER");
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication()
			.getAuthorities();
		List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		updatedAuthorities.add(authority);
		updatedAuthorities.addAll(oldAuthorities);
		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
				SecurityContextHolder.getContext().getAuthentication().getCredentials(),
				updatedAuthorities)
		);
		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
		log.info(authentication1.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
		log.info("Getting messages...");
		return simpleMessageRepository.findAll();
	}

}
