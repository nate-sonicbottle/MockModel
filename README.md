# MockModel

[![Build Status](https://travis-ci.org/nate-sonicbottle/MockModel.svg?branch=master)](https://travis-ci.org/nate-sonicbottle/MockModel) [![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT) 

MockModel Annotation 

This java annotation populates POJOs with mock data to aid in unit testing. A common unit test might look like the following: 

```
@Test
private void thatNewUserGetsCreated() throws Exception {
		// Given
		MemberVO memberVO = new MemberVO(“firstName”, “lastName”);
		when(memberService.createUser(“firstName”,“LastName”)).thenReturn(memberVO)		
		
		// When
		MemberVO result = controller.createUser(“firstName”,“LastName”);	

		// Then
		assertEquals(result, memberVO);
}
```

In the above example, MemberVO had to be populated by hand with test data. This is ok in the above example since MemberVO is only required two fields. But sometimes the POJO required for testing might be bigger. In these cases we can end up doing the following: 

```
@Test
private void thatNewUserGetsCreated() throws Exception {

	// Given
	MemberProfileVO profile = new MemberProfileVO();
	profile.setFirstName = "firstname";
	profile.setLastName = "lastname";
	profile.setDOB = new Date();

	MemberAddressVO address = new MemberAddress();
	address.setHouseNumber(45);
	address.setStreetName("Washing Lane");
	address.setPostalCode("la44 3bw");

	profile.setMemberAddress(address);

	MemberMarketingPermissions permissions = new MemberMarketingPermissions();
	permissions.setEmailOptOut(true);
	permissions.setMailOptOut(true);

	profile.setMemberMarketingPermissions(permissions);

	when(memberService.createUser(“firstName”,“LastName”)).thenReturn(memberVO)		
		
	// When
	MemberVO result = controller.createUser(“firstName”,“LastName”);	

	// Then
	assertEquals(result, memberVO);	
```

This can become more time consuming than nessercary. To expediate this you can use the following annotation. 

```
@MockModel
MemberVO memberVO;

@Before
public void init() throws Exception{
	MockModelProcessor.initMocks(this);
}

@Test
private void thatNewUserGetsCreated() throws Exception {
	// Given 
	when(memberService.createUser(memberVO.getFirstName(), memberVO.getLastName())).thenReturn(memberVO)		
		
	// When
	MemberVO result = controller.createUser(memberVO.getFirstName(), memberVO.getLastName());	

	// Then
	assertEquals(result, memberVO);	
}
```

## How it works 

When ``initMocks()`` is called on ``MockModelProcessor``, it looks for any fields with the ``@MockModel`` annotion. If it finds one of these fields it determines the type of that fields and assigns it a unique value (exception to this is Date & Timestamp). It will attempt to apply the value via setter injection if possible, Otherwise it will use reflection to change the field value. 

## Times when MockModel isn't suitable 

There will be times that using MockModel is not suitable. Mostly this will be when the value of the input data to the test is strictly important. Example if you are testing validation logic. In this circumstances you might be better suited overriding thoose particilur values with manual test data: 

```
@MockModel
MemberVO memberVO;

@Before
public void init() throws Exception{
	MockModelProcessor.initMocks(this);
}

@Test
private void thatNewUserFailsValidationIfUnder13() throws Exception {
	// Given 
	memberVO.setDOB(new Date());
	when(memberService.createUser(memberVO.getFirstName(), memberVO.getLastName())).thenReturn(memberVO)		
		
	// When
	WebResult result = controller.createUser(memberVO.getFirstName(), memberVO.getLastName(), memberVO.getDOB());	

	// Then
	assertEquals("400", result.getResponseCode());	
}
```





