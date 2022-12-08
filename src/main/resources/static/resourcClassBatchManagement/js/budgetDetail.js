	var budgetDetail = [];
            function addForm() {
               let total = document.getElementById('total');
               let overBubget = document.getElementById('overBubget');
               let item = document.getElementById('item');
               let unit = document.getElementById('unit');
               let unitExpense = document.getElementById('unitExpense');
               let quantity = document.getElementById('quantity');
               let  amount = unitExpense.value * quantity.value
               let  tax = document.getElementById('tax');
               let  sum = amount-(amount*(tax.value/100));
               let  note = document.getElementById('note');
    
               let budgetDetailList = {
                     item:item.value
                    ,unit:unit.value
                    ,unitExpense:unitExpense.value
                    ,quantity:quantity.value
                    ,amount:amount
                    ,tax:tax.value
                    ,sum:sum
                    ,note:note.value}
    
                    budgetDetail.unshift(budgetDetailList);
                    loadForm();
                    item.innerText = '';
            };
           
    
            function loadForm() {
                let col1 = document.getElementById('col1');
                let col2 = document.getElementById('col2');
                let col3 = document.getElementById('col3');
                let col4 = document.getElementById('col4');
                let col5 = document.getElementById('col5');
                let col6 = document.getElementById('col6');
                let col7 = document.getElementById('col7');
                let col8 = document.getElementById('col8');
                let icon = document.getElementById('icon');
    
    
                let colum1 = '';
                let colum2 = '';
                let colum3 = '';
                let colum4 = '';
                let colum5 = '';
                let colum6 = '';
                let colum7 = '';
                let colum8 = '';
                let btn = '';
                for (let index = 0; index < budgetDetail.length; index++) {
                    colum1 += '<label class="col-sm-4 border" for="item">Item</label>'
                             +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].item+'" disabled>';
                    
                    colum2 += '<label class="col-sm-4 border" for="unit">Unit</label>'
                               + '<input type="text" class="col-sm-8" value="'+budgetDetail[index].unit+'" disabled>';
                    
                    colum3 += '<label class="col-sm-4 border" for="unitExpense">UX</label>'
                              +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].unitExpense+'" disabled>';
    
                    colum4 += '<label class="col-sm-4 border" for="quantity">Quatity</label>'
                                +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].quantity+'" disabled>';
    
                    colum5 += '<label class="col-sm-4 border" for="amount">Amount</label>'
                               + '<input type="text" class="col-sm-8" value="'+budgetDetail[index].amount+'" disabled>';
    
                    colum6 += '<label class="col-sm-4 border" for="tax">Tax</label>'
                               +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].tax+'" disabled>';
                    
                    colum7 += '<label class="col-sm-4 border" for="sum">Sum</label>'
                               +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].sum+'" disabled>';
    
                    colum8 += '<label class="col-sm-4 border" for="note">Note</label>'
                                +'<input type="text" class="col-sm-8" value="'+budgetDetail[index].note+'" disabled>';
    
                    btn +='<button type="button" onclick="deleteForm('+index+')">-</button>';
                    
                    total += sum; 
                }
    
                col1.innerHTML = colum1;
                col2.innerHTML = colum2;
                col3.innerHTML = colum3;
                col4.innerHTML = colum4;
                col5.innerHTML = colum5;
                col6.innerHTML = colum6;
                col7.innerHTML = colum7;
                col8.innerHTML = colum8;
    
                icon.innerHTML = btn;
                
                total.set
            };
    
            function deleteForm(index) {
                budgetDetail.splice(index,1);
                loadForm();
            };
            
            
            function getBudgetDetail(){
	
        	$.ajax({
                type: 'POST',
                contentType : 'application/json; charset=utf-8',
                url:    'http://localhost:8080/classBatch/getSetBudGetDetail',
                dataType: 'html',
                data: JSON.stringify(budgetDetail),
                asynch: false,
                success : console.log("success")
                
            });
        }