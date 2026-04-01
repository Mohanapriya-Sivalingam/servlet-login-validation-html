/**
 * Login Validation for EMS Project
 */

form.addEventListener('submit', (e) => {
   if (!handleSubmit) {
     e.preventDefault(); // stop submit if invalid
   }

 });

function handleSubmit(){
	
	
	
	const email = document.querySelector("#email").value.trim()
	const password = document.querySelector("#password").value.trim()
	const result =document.querySelector("#formValidationResult")
	
	if(email===""&& password===""){
		result.innerHTML = "All fields Shoud be Filled"
				return false
	}
	if(email ==="" || password ===""){
		result.innerHTML = "Fill all Fields!"
		
		return false
	}
	
	return true
		
		
}