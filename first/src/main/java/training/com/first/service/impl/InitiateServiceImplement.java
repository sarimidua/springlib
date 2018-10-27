package training.com.first.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import training.com.first.repository.iface.RoleRepositoryInterface;
import training.com.first.service.iface.InitiateServiceInterface;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class InitiateServiceImplement implements InitiateServiceInterface{

	@Autowired
	private RoleRepositoryInterface repo;
	
	@Override
	public String getOutput() {
		// TODO Auto-generated method stub
		return "hello word";
	}

}
