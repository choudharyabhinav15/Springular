package com.mediatheque;

import com.mediatheque.model.*;
import com.mediatheque.repository.UserRepository;
import com.mediatheque.security.auth.AnonAuthentication;
import com.mediatheque.security.auth.TokenBasedAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */
@RunWith( SpringRunner.class )
@SpringBootTest(classes = { Application.class })
public abstract class AbstractTest {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected ObjectMapper objectMapper;

	@Before
	public final void beforeAbstractTest() {
		securityContext = Mockito.mock( SecurityContext.class );
		SecurityContextHolder.setContext( securityContext );
		Mockito.when( securityContext.getAuthentication() ).thenReturn( new AnonAuthentication() );
	}

	@After
	public final void afterAbstractTest() {
		SecurityContextHolder.clearContext();
	}

	protected SecurityContext securityContext;

	protected void mockAuthenticatedUser( User user ) {
		mockAuthentication( new TokenBasedAuthentication( user ) );
	}

	private void mockAuthentication( TokenBasedAuthentication auth ) {
		auth.setAuthenticated( true );
		Mockito.when( securityContext.getAuthentication() ).thenReturn( auth );
	}

    protected User buildTestAnonUser() {
        User user = new User();
        user.setUsername("user");
        return user;
    }

	protected User buildTestUser() {
		User user = new User();
		Authority userAuthority = new Authority();
		userAuthority.setName("ROLE_USER");
		List<Authority> userAuthorities = new ArrayList<>();
		userAuthorities.add(userAuthority);
		user.setUsername("user");
		user.setAuthorities(userAuthorities);
		return user;
	}


    protected User buildTestAdmin() {
        Authority userAuthority = new Authority();
        Authority adminAuthority = new Authority();
        userAuthority.setName("ROLE_USER");
        adminAuthority.setName("ROLE_ADMIN");
        List<Authority> adminAuthorities = new ArrayList<>();
        adminAuthorities.add(userAuthority);
        adminAuthorities.add(adminAuthority);
        User admin = new User();
        admin.setUsername("admin");
        admin.setAuthorities(adminAuthorities);
        return admin;
    }

    protected Mediatheque buildMedia() {
		Mediatheque mediatheque = new Mediatheque();
		mediatheque.setName("Montreuil Media");
		return mediatheque;
	}
    
    /*protected Document buildDocument() {
		Document document = new Document();
		//document.setId(1L);
		document.setTitle("mon livre preferer");
		//Localisation localisation = new Localisation();
		//localisation.setId(1L);
		//localisation.setSalle("2");
		//localisation.setRayon("Litterature");
		//document.setLocalization(localisation);
		return document;
	}
*/
	protected Localisation buildLocalization(){
		Localisation localisation = new Localisation();
		//localisation.setId(1L);
		localisation.setSalle("2");
		localisation.setRayon("Litterature");
		return localisation;
	}

	protected Livre buildLivre(){
		Livre document = new Livre();
		document.setLocalization(buildLocalization());
		document.setCode("1111-1111-1111-1111");
		document.setAuthor("Ghiles");
		document.setYear("2018");
		document.setTitle("Test spring Application with Angular client");
		document.setGender("Informatique");
		document.setNbPage(350);
		return document;
	}
}