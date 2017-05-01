/* SecurityUtil.java

{{IS_NOTE
	Purpose:

	Description:

	History:
		Sep 17, 2009 10:48:23 AM, Created by henrichen
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.
Partial Copyright 2004, 2005, 2006 Acegi Technology Pty Limited

{{IS_RIGHT
	This program is distributed under GPL Version 3.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.

	This implementation is based on codes of the Spring Security taglibs 
	written by Ben Alex, Thomas Champagne, Francois Beausoleil, et al.

	As what Apache License required, we have to include following license 
	statement.

	-----

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.

}}IS_RIGHT
 */

package ServiceCentre.ui.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;


@SuppressWarnings("rawtypes")
public class SecurityUtil {

	public static User getUser() {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			try{
				Object p = auth.getPrincipal();
				if(p instanceof User) return (User) p;	
			}catch(RuntimeException e){
				e.printStackTrace();
				throw e;
			}
		}
		return null;
	}

	public static boolean isNoneGranted(String authorities) {
		if (null == authorities || "".equals(authorities)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();

		final Set grantedCopy = retainAll(granted, parseAuthorities(authorities));
		return grantedCopy.isEmpty();
	}

	public static boolean isAllGranted(String authorities) {
		if (null == authorities || "".equals(authorities)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();
		boolean isAllGranted = granted.containsAll(parseAuthorities(authorities)); 
		return isAllGranted;
	}

	public static boolean isAnyGranted(String authorities) {
		if (null == authorities || "".equals(authorities)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();
		final Set grantedCopy = retainAll(granted, parseAuthorities(authorities));
		return !grantedCopy.isEmpty();
	}


	private static Collection<? extends GrantedAuthority> getPrincipalAuthorities() {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (null == currentUser) {
			return Collections.emptyList();
		}
		if ((null == currentUser.getAuthorities()) || (currentUser.getAuthorities().isEmpty())) {
			return Collections.emptyList();
		}
		Collection<? extends GrantedAuthority> granted = currentUser.getAuthorities();
		return granted;
	}

	private static Collection<GrantedAuthority> parseAuthorities(String authorizationsString) {
		final ArrayList<GrantedAuthority> required = new ArrayList<GrantedAuthority>();
		final String[] roles = authorizationsString.split(",");

		for (int i = 0; i < roles.length; i++) {
			String role = roles[i].trim();
			required.add(new SimpleGrantedAuthority(role));
		}
		return required;
	}


	private static Set retainAll(final Collection<? extends GrantedAuthority> granted, final Collection<? extends GrantedAuthority> required) {
		Set<String> grantedRoles = toRoles(granted);
		Set<String> requiredRoles = toRoles(required);
		grantedRoles.retainAll(requiredRoles);

		return toAuthorities(grantedRoles, granted);
	}

	private static Set<String> toRoles(Collection<? extends GrantedAuthority> authorities) {
		final Set<String> target = new HashSet<String>();
		for (GrantedAuthority au : authorities) {

			if (null == au.getAuthority()) {
				throw new IllegalArgumentException(
						"Cannot process GrantedAuthority objects which return null from getAuthority() - attempting to process "
								+ au.toString());
			}

			target.add(au.getAuthority());
		}

		return target;
	}
	/**
	 * 
	 * @param grantedRoles
	 * @param granted
	 * @return
	 */
	private static Set<GrantedAuthority> toAuthorities(Set<String> grantedRoles, Collection<? extends GrantedAuthority> granted) {
		Set<GrantedAuthority> target = new HashSet<GrantedAuthority>();

		for (String role : grantedRoles) {
			for ( GrantedAuthority authority: granted) {

				if (authority.getAuthority().equals(role)) {
					target.add(authority);
					break;
				}
			}
		}
		return target;
	}
	/**
	 * test if current user principal contains all given authorities
	 *   
	 * @param authorities the roles will be checked 
	 */
	public static void assertAll(String... authorities) {

		if (null == authorities || authorities.length == 0) {
			return;
		}

		final ArrayList<GrantedAuthority> required = new ArrayList<GrantedAuthority>();
		for (String auth : authorities) {
			required.add(new SimpleGrantedAuthority(auth));
		}

		final Collection<? extends GrantedAuthority> granted = getPrincipalAuthorities();
		if(!granted.containsAll(required)){
			Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
			throw new AccessDeniedException("The current principal doesn't has enough authority. Authentication: "+
					(currentUser==null ? "" : currentUser.getName()));
		}
	}







}
