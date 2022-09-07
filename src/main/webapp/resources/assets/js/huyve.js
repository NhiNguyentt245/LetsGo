/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function huyVe(endpoint)
{
    
//    let d = document.getElementById("loading" + id);
//    let p = document.getElementById("delete" + id);
//    let q = document.getElementById("edit" + id);
//    d.style.display = "block";
//    p.style.display = "none";
//    q.style.display = "none";
    fetch(endpoint, {
        method: "post"
    }).then(function (res)
    {
        if (res.status === 204)
            location.reload();
        else
            alert(res.status)
    }).catch(function (err)
    {
        console.error(err);
    });
//    d.style.display = "none";

}