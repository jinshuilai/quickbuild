package ${basePackage}.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ${basePackage}.util.ObjectAnalyzer;
import ${basePackage}.base.HtmlParams;

@Entity
@Table(name="${modelNameLowerCamel}")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class ${modelNameUpperCamel} implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seqid")
	private Integer seqid;
	public Integer getSeqid() {
		return seqid;
	}
	public void setSeqid(Integer seqid) {
		this.seqid = seqid;
	}
	
<#list columns as col>
	<#if col.type == "datatime">
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true)
	@Column(name="${col.name}")
	private Date ${col.filed};
	
	public Date get${col.filedUp}() {
		return ${col.filed};
	}
	public void set${col.filedUp}(Date ${col.filed}) {
		this.${col.filed} = ${col.filed};
	}
	
	@HtmlParams(search=true)
	@Transient
	private Date ${col.filed}End;
	
	public Date get${col.filedUp}End() {
		return ${col.filed}End;
	}
	public void set${col.filedUp}End(Date ${col.filed}End) {
		this.${col.filed}End = ${col.filed}End;
	}
	
	<#else>
	@HtmlParams(search=true,list=true,input=true,detail=true,must=true)
	@Column(name="${col.name}")
	private ${col.javaType} ${col.filed};
	
	public ${col.javaType} get${col.filedUp}() {
		return ${col.filed};
	}
	public void set${col.filedUp}(${col.javaType} ${col.filed}) {
		this.${col.filed} = ${col.filed};
	}
	
	</#if>
</#list>
	
	@Override
	public String toString() {
		return ObjectAnalyzer.toString(this);
	}
	
}
