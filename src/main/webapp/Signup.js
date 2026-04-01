/**
 * FormValidation Js File for SignUP Page
 */

function handleSubmit(){
	const username =document.querySelector('#username').value.trim()
	const email = document.querySelector('#email').value.trim()
	const password = document.querySelector('#password').value.trim()
	const cpassword = document.querySelector('#cpassword').value.trim()
	
	const result = document.querySelector('#formValidationResult')
	
	if(username==="" || email === "" || password === "" || cpassword === ""){
		result.innerHTML = "All fields Shoud be Filled"
		return false
	}
	
	if(password !== cpassword){
		result.innerHTML = "Confirm Password does not Matches with Password!"
		return false
	}
	
	return true
	
}