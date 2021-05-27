package br.mil.marinha.sisconvapi.history.listener;



import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import br.mil.marinha.sisconvapi.history.entity.AuditEntity;

@Component
public class AuditListener implements RevisionListener {
	
	@Override
	public void newRevision(Object revisionEntity) {
		// TODO Auto-generated method stub
		AuditEntity revEntity = (AuditEntity) revisionEntity;  
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Object principal = auth.getPrincipal();
    	Object details = auth.getDetails();
    	
		if (principal instanceof UserDetails)
			 revEntity.setRevision_Username(((UserDetails) principal).getUsername());
		
		if (details instanceof WebAuthenticationDetails) {
			 revEntity.setRevision_UserIp(((WebAuthenticationDetails) details).getRemoteAddress());
			 System.out.println(((WebAuthenticationDetails) details).getRemoteAddress());
			 System.out.println(((WebAuthenticationDetails) details).getSessionId());
		}
	}

}
