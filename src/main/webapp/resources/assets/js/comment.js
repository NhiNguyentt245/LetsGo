/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




window.onload = function(){
    let date = document.querySelectorAll(".cmt > i")
    for(let i = 0; i < date.length ; i++)
    {
        let d = date[i];
        d.innerText = moment(d.innerText).fromNow();
    }
}

function addComment(idcx)
{   
   
    fetch("/LetsGo/api/addComment",{
    
        method: "POST",
        body: JSON.stringify({
            "content":document.getElementById("textcomment").value,
            "idCX":idcx
    
        }),
        
        headers:{
            "Content-Type":"application/json"
        }
        
    }).then(function(){
         location.reload();
    });
}

function getUrlne(i)
{

    document.getElementById("Hpage").value = i;
    document.getElementById("click").click();
}