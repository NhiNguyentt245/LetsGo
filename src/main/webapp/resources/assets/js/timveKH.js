/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */






function setValue(id, i)
{
    document.getElementById(id).value = i;
}

function setSubmitPage()
{
    document.getElementById("submitpage").click();
}

function setpicGhe(id)
{
    let el = document.getElementById(id)
    let e2 = document.getElementById("ip" + id);
    if (e2.value == 1)
    {
        el.setAttribute("class", "bi bi-file-check-fill");
        e2.value = 2;

    } else if (e2.value == 2)
    {
        el.setAttribute("class", "bi bi-file");
        e2.value = 1;

    }



}
function chonGhe(idcx, idcxcn) {

    fetch(`/LetsGo/api/timve/${idcx}?idCXCN=${idcxcn}`, {
        method: "POST"}).then(function (res) {
        return res.json();

    }).then(function (data) {

        let el = document.getElementById("soghe");
        let tt = document.getElementById("tongTien");
        var s = "";
        var k;

        if (Object.keys(data).length != 0) {
            document.getElementById("btChiTiet").disabled = false;
            var i
            for (i = 0; i < data.length; i++)
            {
                s += data[i].cxcn.maGhe
                if (i != data.length - 1)
                {
                    s += ","
                }
            }


            k = data[0].donGia * data.length;
            k = k.toLocaleString('it-IT', {style: 'currency', currency: 'VND'});

        } else
        {
            document.getElementById("btChiTiet").disabled = true;
            s = "Chưa chọn";
            k = "0";
        }

        el.innerText = s;
        tt.innerText = k;

    })

}
function btcheck()
{
    alert('dddđ');
    bt = document.getElementById("btChiTiet").value;
  if(bt == 1)
  {
        alert("Bạn Chưa Chọn Ghế Nào");
        return false;
  }
        
}


function checkTT()
{
        alert("Ghế đã có người chọn rồi, bạn chọn lại giúp mình nhé T.T");
}
