package com.dcb.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(
            name ="course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;
    private String url;

    @OneToOne(
            cascade = CascadeType.ALL, //Cascading meaning when we perform some action on the target entity,
                                       // the same action will be applied to the associated entity.
            fetch = FetchType.LAZY, // LAZY Fetches data of CourseMaterial only and loads data of Course only on-demand, EAGER - fetches data of both CourseMaterial and Course
            optional = false        // CourseMaterial is not optional when saving a course
    )
    @JoinColumn(
            name="course_id",
            referencedColumnName = "courseId"   // foreign key which is extra column
    )
    private Course course;
}
