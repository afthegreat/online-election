
package com.onlineelection.system.UserModelService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.onlineelection.system.UserModelService.Entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findByStudentId(String studentId);

    Account findByEmail(String email);

    Account findByStudentIdAndPassword(String studentId, String password);

    Account findByStudentIdAndEmail(String studentId, String email);

    // Add custom query methods if needed
}
