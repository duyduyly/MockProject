package com.example.demo.utils;

import java.sql.Connection;
import java.sql.Timestamp;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Trainer;
import com.example.demo.entity.User;

public class WebUtils {
	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

	private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}

	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}

	public static void storeLoginedUser(HttpSession session, User loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
		System.out.println("Stored " + loginedUser.getUsername() + " to Session");
	}

	public static void removeLoginedUser(HttpSession session, User loginedUser) {
		session.removeAttribute("loginedUser");
		System.out.println("Remove " + loginedUser.getUsername() + " from Session");
	}

	public static User getLoginedUser(HttpSession session) {
		User loginedUser = (User) session.getAttribute("loginedUser");
		return loginedUser;
	}

	public static void storeUserCookie(HttpServletResponse response, Trainer user) {
		System.out.println("Store user cookie");
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUsername());
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(24 * 60 * 60);
		response.addCookie(cookieUserName);
	}

	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}

	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
					cookie.setValue(null);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					System.out.println("cookie deleted");
				}
			}
	}

	public static Timestamp getSQLTimestamp() {
		java.util.Date utilDate = new java.util.Date();
		Timestamp sqlTimestamp = new Timestamp(utilDate.getTime());
		return sqlTimestamp;
	}

	public static void setRedirectAttributes(RedirectAttributes ra, String errorString, String resultString,
			String classBatchId, String selectedTab) {
		ra.addFlashAttribute("errorString", errorString);
		ra.addFlashAttribute("resultString", resultString);
		ra.addFlashAttribute("classBatchId", classBatchId);
		ra.addFlashAttribute("selectedTab", selectedTab);
	}
}
