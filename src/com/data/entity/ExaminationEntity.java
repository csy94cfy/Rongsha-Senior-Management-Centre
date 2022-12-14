package com.data.entity;

import com.framework.core.BaseManageEntity;

public class ExaminationEntity extends BaseManageEntity{
	private String year;//年份
	private String departmentId;//部门代码
	private String departmentName;//部门名称
	private String bureauEmployment;//用人司局
	private String institutionalNature;//机构性质
	private String institutionalHierarchy;//机构层级
	private String jobAttribute;//职位属性
	private String jobName;//职位名称
	private String jobBrief;//职位简介
	private String positionId;//职位代码
	private String examinationCategory;//考试类别
	private String recruitmentNum;//招考人数
	private String major;//专业
	private String education;//学历
	private String degree;//学位
	private String englishLevel;//英语水平
	private String politicalOutlook;//政治面貌
	private String jobMin;//基层工作最低年限
	private String threeOne;//‘三支一扶’大学生
	private String person;//西部志愿者
	private String county;//大学生村官
	private String teacher;//特岗计划教师
	private String checkUp;//无限制
	private String checkTeam;//是否组织专业考试
	private String radio;//面试人员比例
	private String other;//其他条件
	private String remark;//备注
	private String jobPlacemen;//职位分布
	private String cityName;//所在省份
	private String website;//部门网站
	private String phone1;//咨询电话1
	private String phone2;//咨询电话2
	private String phone3;//咨询电话3
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBureauEmployment() {
		return bureauEmployment;
	}
	public void setBureauEmployment(String bureauEmployment) {
		this.bureauEmployment = bureauEmployment;
	}
	public String getInstitutionalNature() {
		return institutionalNature;
	}
	public void setInstitutionalNature(String institutionalNature) {
		this.institutionalNature = institutionalNature;
	}
	public String getInstitutionalHierarchy() {
		return institutionalHierarchy;
	}
	public void setInstitutionalHierarchy(String institutionalHierarchy) {
		this.institutionalHierarchy = institutionalHierarchy;
	}
	public String getJobAttribute() {
		return jobAttribute;
	}
	public void setJobAttribute(String jobAttribute) {
		this.jobAttribute = jobAttribute;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobBrief() {
		return jobBrief;
	}
	public void setJobBrief(String jobBrief) {
		this.jobBrief = jobBrief;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getExaminationCategory() {
		return examinationCategory;
	}
	public void setExaminationCategory(String examinationCategory) {
		this.examinationCategory = examinationCategory;
	}
	public String getRecruitmentNum() {
		return recruitmentNum;
	}
	public void setRecruitmentNum(String recruitmentNum) {
		this.recruitmentNum = recruitmentNum;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEnglishLevel() {
		return englishLevel;
	}
	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}
	public String getPoliticalOutlook() {
		return politicalOutlook;
	}
	public void setPoliticalOutlook(String politicalOutlook) {
		this.politicalOutlook = politicalOutlook;
	}
	public String getJobMin() {
		return jobMin;
	}
	public void setJobMin(String jobMin) {
		this.jobMin = jobMin;
	}
	public String getThreeOne() {
		return threeOne;
	}
	public void setThreeOne(String threeOne) {
		this.threeOne = threeOne;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getCheckUp() {
		return checkUp;
	}
	public void setCheckUp(String checkUp) {
		this.checkUp = checkUp;
	}
	public String getCheckTeam() {
		return checkTeam;
	}
	public void setCheckTeam(String checkTeam) {
		this.checkTeam = checkTeam;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJobPlacemen() {
		return jobPlacemen;
	}
	public void setJobPlacemen(String jobPlacemen) {
		this.jobPlacemen = jobPlacemen;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	@Override
	public String toString() {
		return "ExaminationEntity [year=" + year + ", departmentId=" + departmentId + ", departmentName="
				+ departmentName + ", bureauEmployment=" + bureauEmployment + ", institutionalNature="
				+ institutionalNature + ", institutionalHierarchy=" + institutionalHierarchy + ", jobAttribute="
				+ jobAttribute + ", jobName=" + jobName + ", jobBrief=" + jobBrief + ", positionId=" + positionId
				+ ", examinationCategory=" + examinationCategory + ", recruitmentNum=" + recruitmentNum + ", major="
				+ major + ", education=" + education + ", degree=" + degree + ", englishLevel=" + englishLevel
				+ ", politicalOutlook=" + politicalOutlook + ", jobMin=" + jobMin + ", threeOne=" + threeOne
				+ ", person=" + person + ", county=" + county + ", teacher=" + teacher + ", checkUp=" + checkUp
				+ ", checkTeam=" + checkTeam + ", radio=" + radio + ", other=" + other + ", remark=" + remark
				+ ", jobPlacemen=" + jobPlacemen + ", website=" + website + ", phone1=" + phone1 + ", phone2=" + phone2
				+ ", phone3=" + phone3 + "]";
	}
}