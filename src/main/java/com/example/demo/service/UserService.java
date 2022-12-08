package com.example.demo.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Trainer;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.WebUtils;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.Role;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void save(User user, String password, List<Role> listOfRoles) {
		user.setPassword(bCryptPasswordEncoder().encode(password));
		user.setSetOfRoles(new HashSet<>(listOfRoles));
		userRepository.save(user);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getSetOfRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

	public void logUserIntoSession(UserDetails userDetails, HttpSession session) {
		Collection<? extends GrantedAuthority> userAuthorities = userDetails.getAuthorities();
		System.out.println(userDetails.getUsername() + " has these Authorities: ------------");
		for (GrantedAuthority grantedAuthority : userAuthorities) {
			System.out.println(grantedAuthority.getAuthority());
		}
		System.out.println("-----------------------------------");

		String userFullName = null;
		User sessionUser = userRepository.findByUsername(userDetails.getUsername());
		if (sessionUser instanceof Trainer) {
			userFullName = ((Trainer) sessionUser).getTrainerProfile().getFullName();
		} else if (sessionUser instanceof ClassAdmin) {
			userFullName = ((ClassAdmin) sessionUser).getClassAdminProfile().getFullName();
		} else if (sessionUser instanceof FAManager) {
			userFullName = ((FAManager) sessionUser).getFullName();
		} else if (sessionUser instanceof FARec) {
			userFullName = ((FARec) sessionUser).getFullName();
		} else if (sessionUser instanceof DeliveryManager) {
			userFullName = ((DeliveryManager) sessionUser).getFullName();
		}
		System.out.println(userFullName + " - " + sessionUser.getUsername() + " has logged in.");
		session.setAttribute("userFullName", userFullName);
		WebUtils.storeLoginedUser(session, sessionUser);
	}
}