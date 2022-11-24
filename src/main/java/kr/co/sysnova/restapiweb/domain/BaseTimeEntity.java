package kr.co.sysnova.restapiweb.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

/**
 * BaseTimeEntity는 모든 Entity의 상위 클래스가 되어 Entity들의
 * createdDate, modifiedDate를 자동으로 관리하는 역할
 * 매번 DB에 Entity를 삽입하기 전 / 갱신하기 전에 날짜 데이터를 등록/수정
 * 
 * 이제 등록일 / 수정일이 필요한 모든 Entity는 BaseTimeEntity만 상속받으면 된다.
 */
@Getter
// JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들
// (createdDate, modifiedDate)도 Column으로 인식하도록 합니다.
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing(감사) 기능을 포함시킨다.
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    // Entity가 생성되어 저장될 때 시간이 자동으로 저장된다
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity의 값을 변경할 때 시간이 자동으로 저장된다
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}