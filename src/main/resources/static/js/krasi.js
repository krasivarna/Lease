function GetVendorId() {
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('vendorNo');
    No.value=currentNo;
    HideLookup();
}
function GetPayoffId(){
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('payEntry');
    No.value=currentNo;
    HideLookup();
}
function SearchVendor(inp,main){
   if (inp.length>=2){
       CreateLookupVendor(inp,main);
   }
}
function CreateLookupCountry(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Country no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Country name';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/countrysmalllist';
    } else {
        restLink ='http://localhost:8080/countrysmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.classList.add('a_lookup');
            a.href = '/countrycard/' + item.no;
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.name;
            span.ondblclick=GetCountryId;

            Name.appendChild(span);
            row.appendChild(Name);
            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.classList.add('a_lookup');
    anew.href='/countrycard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.classList.add('a_lookup');
    alist.href='/countrylist';
    alist.textContent='list';

    ShowLookup(event);
}
function ShowLookup(event) {
    let grid = document.getElementById('lookup');
    grid.style.zIndex = '1';
    grid.style.width = '400px';
    grid.style.height = '300px';
    grid.style.left = event.clientX + 'px';
    grid.style.top = event.clientY + 'px';
    grid.style.display="block";
}
function SearchCountry(inp,main){
    if (inp.length>=2){
        CreateLookupCountry(inp,main)
    }
}
function GetCountryId() {
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('country');
    No.value=currentNo;
    HideLookup();
}
function HideLookup(){
    let list=document.getElementById('lookup');
    if (list!=null) {
        list.style.display = "none";
        list.remove();
    }
}
function GetVehicleId(){
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('vehicleNo');
    No.value=currentNo;
    HideLookup();
}
function CreateLookupVendor(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Vendor no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Vendor name';
    let VatCaption=document.createElement('th');
    VatCaption.textContent='Vat registration no.';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);
    rowHead.appendChild(VatCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/vendorsmalllist';
    } else {
        restLink ='http://localhost:8080/vendorsmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.classList.add('a_lookup');
            a.href = '/vendorcard/' + item.no;
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.name;
            span.ondblclick=GetVendorId;

            Name.appendChild(span);
            row.appendChild(Name);

            let Vat = document.createElement('td');
            Vat.setAttribute('role', 'gridcell');
            let spanVat = document.createElement('span');
            spanVat.textContent = item.vatRegistration;
            spanVat.ondblclick=GetVendorId;

            Vat.appendChild(spanVat);
            row.appendChild(Vat);

            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.classList.add('a_lookup');
    anew.href='/vendorcard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.classList.add('a_lookup');
    alist.href='/vendorlist';
    alist.textContent='list';

    ShowLookup(event);
}
function CreateLookupVehicle(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Vehicle no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Vehicle plate no.';
    let ModelCaption=document.createElement('th');
    ModelCaption.textContent='Model';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);
    rowHead.appendChild(ModelCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/vehiclesmalllist';
    } else {
        restLink ='http://localhost:8080/vehiclesmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.href = '/vehiclecard/' + item.no;
            a.classList.add('a_lookup');
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.numberPlate;
            span.ondblclick=GetVehicleId;

            Name.appendChild(span);
            row.appendChild(Name);

            let Model = document.createElement('td');
            Model.setAttribute('role', 'gridcell');
            let spanModel = document.createElement('span');
            spanModel.textContent = item.vehicleModel;
            spanModel.ondblclick=GetVehicleId;

            Model.appendChild(spanModel);
            row.appendChild(Model);

            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.classList.add('a_lookup');
    anew.href='/vehiclecard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.classList.add('a_lookup');
    alist.href='/vehiclelist';
    alist.textContent='list';

    ShowLookup(event);
}
function SearchVehicle(inp,main){
    if (inp.length>=2){
        CreateLookupVehicle(inp,main);
    }
}
function SearchPay(inp,main){
    if (inp.length>=2){
        CreateLookupPayplan(inp,main)
    }
}
function CreateLookupPayplan(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Entry no.';
    let contractCaption=document.createElement('th');
    contractCaption.textContent='Contract no';
    let monthCaption=document.createElement('th');
    monthCaption.textContent='Month';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(contractCaption);
    rowHead.appendChild(monthCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/payoffsmalllist';
    } else {
        restLink ='http://localhost:8080/payoffsmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.classList.add('a_lookup');
            a.textContent = item.entryNo;

            No.appendChild(a);
            row.appendChild(No);

            let contract = document.createElement('td');
            contract.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.contractNo;
            span.ondblclick=GetPayoffId;

            contract.appendChild(span);
            row.appendChild(contract);

            let month = document.createElement('td');
            month.setAttribute('role', 'gridcell');
            let spanMonth = document.createElement('span');
            spanMonth.textContent = item.month;
            spanMonth.ondblclick=GetPayoffId;

            month.appendChild(spanMonth);
            row.appendChild(month);

            tbody.appendChild(row);
        }));

    ShowLookup(event);
}


