package com.onlineelection.system.RegisterationService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineelection.system.RegisterationService.Entity.ElectionComNominees;
import com.onlineelection.system.RegisterationService.Entity.MemberOfParliament;

@Repository
public interface MemberOfParlamentRepository extends JpaRepository<MemberOfParliament, String> {
    MemberOfParliament findByStudentId(String studentId);
}
