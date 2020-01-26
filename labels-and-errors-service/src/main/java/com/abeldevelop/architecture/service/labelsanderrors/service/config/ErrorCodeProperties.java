package com.abeldevelop.architecture.service.labelsanderrors.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties("labels-and-errors-error-code")
public class ErrorCodeProperties {

}