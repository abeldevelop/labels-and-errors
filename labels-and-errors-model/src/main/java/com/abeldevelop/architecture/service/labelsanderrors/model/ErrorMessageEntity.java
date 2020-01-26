package com.abeldevelop.architecture.service.labelsanderrors.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

import com.abeldevelop.architecture.library.common.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ToString
@Table(name = "error_messages", schema = "labels_and_errors_db")
@Entity
public class ErrorMessageEntity extends BaseEntity {

	@Column(name = "serviceName", nullable = false)
    private String serviceName;
    
    @Column(name = "language_code", nullable = false)
    private String languageCode;
    
    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "message", nullable = false)
    private String message;
    
    @Version
    @Column(name = "version", nullable = false)
    private Integer version;
    
}
