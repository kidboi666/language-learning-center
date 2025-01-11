package jpabasic.reserve.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hotel_info")
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // EntityManager@persist() 호출 시점에 INSERT 쿼리 실행
  // persist() 실행할 때 객체에 식별자 값 할당됨
  @Column(name = "hotel_id")
  private Long id;

  @Column(name = "nm")
  private String name;

  private int year;

  @Enumerated(EnumType.STRING)
  private Grade grade;
  private LocalDateTime created;

  @Column(name = "modified")
  private LocalDateTime lastModified;

  protected Hotel() {

  }

  public Hotel(Long id, String name, int year, Grade grade) {

    this.id = id;
    this.name = name;
    this.year = year;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }

  public Grade getGrade() {
    return grade;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  @Override
  public String toString() {
    return "Hotel{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", year=" + year +
        ", grade=" + grade +
        ", created=" + created +
        ", lastModified=" + lastModified +
        '}';
  }
}

/*
  엔티티 클래스 제약 조건 (스펙 기준)
  - @Entity 적용해야 함
  - @Id 적용해야 함
  - 인자 없는 기본 생성자 필요
  - 기본 생성자는 public이나 protected여야 함
  - 최상위 클래스여야 함
  - final이면 안 됨
 */

/*
  주요 매핑 애노테이션
    - @Entity, @Table, @Id, @Column, @Enumerated

  엔티티 클래스 제약
    - 기본 생성자 필요 등 몇 가지 제약 있음

  접근 타입
    - 필드 접근(*), 프로퍼티 접근
 */