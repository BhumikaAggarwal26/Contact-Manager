let currentTheme = getTheme();

document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme();
});


function changeTheme(){
    document.querySelector("html").classList.add(currentTheme);

    const changeThemeButton = document.querySelector("#change_Theme");

    changeThemeButton.querySelector("span").textContent = currentTheme=="light"?"Dark":"Light";
    
    changeThemeButton.addEventListener("click", ()=>{
        const oldTheme = currentTheme;

        if(currentTheme == "light"){
            currentTheme = "dark";
        } else {
            currentTheme = "light";
        }

    setTheme(currentTheme);
    document.querySelector("html").classList.remove(oldTheme);
    document.querySelector("html").classList.add(currentTheme);

    changeThemeButton.querySelector("span").textContent = currentTheme=="light"?"Dark":"Light";
    
});
}

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    let theme = localStorage.getItem("theme");
    return theme?theme:"light";
}