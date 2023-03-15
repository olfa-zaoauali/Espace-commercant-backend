package com.PFE.Espacecommercant.Authen.Repository;

import com.PFE.Espacecommercant.Authen.users.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
