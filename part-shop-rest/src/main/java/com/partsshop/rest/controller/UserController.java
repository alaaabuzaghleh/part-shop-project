package com.partsshop.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.partsshop.rest.dto.RestMessage;
import com.partsshop.rest.dto.UserActivationRest;
import com.partsshop.rest.model.User;
import com.partsshop.rest.repo.UserRepo;
import com.partsshop.rest.security.CurrentUser;
import com.partsshop.rest.security.UserPrincipal;
import com.partsshop.rest.service.UserActivationService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private UserActivationService service;
	@Value("${app.activationCodeExpireInMs}")
	private long activationCodeAge;
	@Autowired
	private UserRepo userRepo;

	@GetMapping("/roles")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<?> getUserRoles(@CurrentUser UserPrincipal currentUser,
			@RequestHeader("Accept-Language") Locale locale) {
		List<String> roles = new ArrayList<>();
		currentUser.getAuthorities().forEach(role -> roles.add(role.getAuthority()));

		if (roles.size() > 0) {
			return new ResponseEntity<>(roles, HttpStatus.OK);
		} else {
			List<String> ls = new ArrayList<>();
			ls.add(this.messageSource.getMessage("user.roles.notfound", null, locale));
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/activation/{activation-code}")
	public ResponseEntity<?> getActivationCode(@PathVariable("activation-code") String code,
			@RequestHeader("Accept-Language") Locale locale) {
		UserActivationRest activationCode = this.service.findByCode(code);
		if (activationCode == null) {
			List<String> ls = new ArrayList<>();
			ls.add(this.messageSource.getMessage("ActivationCode.error", null, locale));
			return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND);
		} else {
			// activation code must be withen 15 minute
			boolean isExpired = (activationCode.getCreationDate() + activationCodeAge) < new Date().getTime() ? true
					: false;

			if (isExpired) {
				List<String> ls = new ArrayList<>();
				ls.add(this.messageSource.getMessage("user.activation.expiredCode", null, locale));
				this.service.removeActivationCode(activationCode);
				return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND);
			}
			User user = userRepo.findByEmail(activationCode.getUserEmail()).orElse(null);
			if (user != null) {
				user.setEnabled(true);
				this.userRepo.save(user);
				List<String> ls = new ArrayList<>();
				ls.add(this.messageSource.getMessage("ActivationCode.success", null, locale));
				this.service.removeActivationCode(activationCode);
				return new ResponseEntity<>(new RestMessage(true, ls), HttpStatus.OK);
			} else {
				List<String> ls = new ArrayList<>();
				ls.add(this.messageSource.getMessage("ActivationCode.error", null, locale));
				return new ResponseEntity<>(new RestMessage(false, ls), HttpStatus.NOT_FOUND);

			}

		}
	}

}
