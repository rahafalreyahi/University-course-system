package com.university.system.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotEmpty
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
      name = "course_student",
      joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
//    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();


//    @Override
//    public String toString() {
//        return "Student [studentId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email ;
//    }
////
//    @Override
//    public int hashCode() {
//        return id != null ? id.hashCode() : 0;
//    }
}
