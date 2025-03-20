package vn.edu.stu.backend_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.edu.stu.backend_service.common.EmployeeStatus;
import vn.edu.stu.backend_service.common.Gender;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name",length = 255)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 255)
    private Gender gender;

    @Column(name = "phone",length = 255)
    private String phone;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "joining_date")
    private LocalDateTime joiningDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status", length = 255)
    private EmployeeStatus employeeStatus;

    @Column(name = "created_at", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "employee")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private PositionEntity position;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SalaryEntity> salaries;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ContractEntity> contracts;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
}
