package com.matrimony.codewithnitin.payload;

public class UserProfileRequest {

	private BasicSearch basicSearch;
	private AdvanceSearch advanceSearch;

	public UserProfileRequest(BasicSearch basicSearch, AdvanceSearch advanceSearch) {
		this.basicSearch = basicSearch;
		this.advanceSearch = advanceSearch;
	}

	public BasicSearch getBasicSearch() {
		return basicSearch;
	}

	public void setBasicSearch(BasicSearch basicSearch) {
		this.basicSearch = basicSearch;
	}

	public AdvanceSearch getAdvanceSearch() {
		return advanceSearch;
	}

	public void setAdvanceSearch(AdvanceSearch advanceSearch) {
		this.advanceSearch = advanceSearch;
	}

	public static class BasicSearch {

		// BASICSEARCH PARAMETERS
		private String religion;
		private String birthPlace;
		private String nativePlace;
		private String marriageStatus;
		private String motherTongue;
		private String state;
		private String subCast;
		private String cast;
		private String country;
		private String townCity;
		private String minHeights;
		private String maxHeights;
		private Integer minAge;
		private Integer maxAge;
		private String gender;

		public BasicSearch() {
			super();
		}

		public String getReligion() {
			return religion;
		}

		public void setReligion(String religion) {
			this.religion = religion;
		}

		public String getBirthPlace() {
			return birthPlace;
		}

		public void setBirthPlace(String birthPlace) {
			this.birthPlace = birthPlace;
		}

		public String getMarriageStatus() {
			return marriageStatus;
		}

		public void setMarriageStatus(String marriageStatus) {
			this.marriageStatus = marriageStatus;
		}

		public String getMotherTongue() {
			return motherTongue;
		}

		public void setMotherTongue(String motherTongue) {
			this.motherTongue = motherTongue;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getSubCast() {
			return subCast;
		}

		public void setSubCast(String subCast) {
			this.subCast = subCast;
		}

		public String getCast() {
			return cast;
		}

		public void setCast(String cast) {
			this.cast = cast;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getTownCity() {
			return townCity;
		}

		public void setTownCity(String townCity) {
			this.townCity = townCity;
		}

		public String getMinHeights() {
			return minHeights;
		}

		public void setMinHeights(String minHeights) {
			this.minHeights = minHeights;
		}

		public String getMaxHeights() {
			return maxHeights;
		}

		public void setMaxHeights(String maxHeights) {
			this.maxHeights = maxHeights;
		}

		public Integer getMinAge() {
			return minAge;
		}

		public void setMinAge(Integer minAge) {
			this.minAge = minAge;
		}

		public Integer getMaxAge() {
			return maxAge;
		}

		public void setMaxAge(Integer maxAge) {
			this.maxAge = maxAge;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getNativePlace() {
			return nativePlace;
		}

		public void setNativePlace(String nativePlace) {
			this.nativePlace = nativePlace;
		}

	}

	public static class AdvanceSearch {
		// AdvanceSearch Parameters
		private String qualification;
		private String stream;
		private String course;
		private String workingWith;
		private String eatingHabits;
		private String smoking;
		private String drinking;
		private String gothram;
		private String zodiac;
		private String bloodGroup;
		private String mangalik;

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}

		public String getStream() {
			return stream;
		}

		public void setStream(String stream) {
			this.stream = stream;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getWorkingWith() {
			return workingWith;
		}

		public void setWorkingWith(String workingWith) {
			this.workingWith = workingWith;
		}

		public String getEatingHabits() {
			return eatingHabits;
		}

		public void setEatingHabits(String eatingHabits) {
			this.eatingHabits = eatingHabits;
		}

		public String getSmoking() {
			return smoking;
		}

		public void setSmoking(String smoking) {
			this.smoking = smoking;
		}

		public String getDrinking() {
			return drinking;
		}

		public void setDrinking(String drinking) {
			this.drinking = drinking;
		}

		public String getGothram() {
			return gothram;
		}

		public void setGothram(String gothram) {
			this.gothram = gothram;
		}

		public String getZodiac() {
			return zodiac;
		}

		public void setZodiac(String zodiac) {
			this.zodiac = zodiac;
		}

		public String getBloodGroup() {
			return bloodGroup;
		}

		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}

		public String getMangalik() {
			return mangalik;
		}

		public void setMangalik(String mangalik) {
			this.mangalik = mangalik;
		}
	}
}
