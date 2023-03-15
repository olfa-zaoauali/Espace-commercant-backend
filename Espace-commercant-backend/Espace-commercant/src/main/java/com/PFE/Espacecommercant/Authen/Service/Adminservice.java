package com.PFE.Espacecommercant.Authen.Service;

import com.PFE.Espacecommercant.Authen.DTO.RegisterAdminResponsedto;
import com.PFE.Espacecommercant.Authen.DTO.RegisterRequest;

import java.util.List;

public interface Adminservice {
    List<RegisterAdminResponsedto> findAll();
}
