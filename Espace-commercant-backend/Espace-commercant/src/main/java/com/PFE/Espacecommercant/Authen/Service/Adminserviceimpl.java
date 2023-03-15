package com.PFE.Espacecommercant.Authen.Service;

import com.PFE.Espacecommercant.Authen.DTO.RegisterAdminResponsedto;
import com.PFE.Espacecommercant.Authen.Repository.AdminRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class Adminserviceimpl implements Adminservice {
     private AdminRepository adminRepository;
     private ModelMapper modelMapper;

    public Adminserviceimpl(AdminRepository adminRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RegisterAdminResponsedto> findAll() {
        return adminRepository.findAll()
                .stream().map(el -> modelMapper.map(el, RegisterAdminResponsedto.class)).collect(Collectors.toList());
    }
}
