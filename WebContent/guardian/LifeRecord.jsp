main{
    margin-top: 52px;
    margin-left:  auto; 
    margin-right: auto;   
}

.bb{
  /* display: block; */
  margin-top: 10px;
  margin-bottom: 10px;
  text-align: right;
  width: 100vw;
  /* background-color: aqua; */
}
.button{
  /* display: inline; */
  padding: 5px;
  margin-right: 10px;
}


.title1, #body1 {
    border: 2px solid rgb(0, 128, 0);
}
.title2, #body2 {
    border: 2px solid rgb(230, 230, 0);
}
.title3, #body3 {
    border: 2px solid rgb(200, 15, 15);
}
.title4, #body4 {
  border: 2px solid rgb(15, 104, 200);
}

.tab-title {
    width: 85px;
    padding: 5px 5px;
    text-align: center;
    display: table; 
}

.tab-body {
  display: none;
    width: 220px;
    height: 150px;
    padding: 10px;
}

/* radio non-display */
.container .radio {
  display: none;
}

/* tabs position */
.container {
    display: flex;
    flex-wrap: wrap;
    justify-content: center; 
    /* background-color: violet; */
}

.container::after {
    content: "";
    width: 100%;
}

/* .container .tab-title {
    position: relative;
} */

.container .tab-body {
    order: 1;
    margin-top: 10px;
}

/* tab's body init */


/* tab's color */
.add-control .radio:checked + .tab-title {
    color: #000000;
}

#tab1:checked ~ .title1 {
    background: rgba(0, 128, 0, 0.5);   
}

#tab2:checked ~ .title2 {
    background: rgba(255, 255, 0, 0.5);
}

#tab3:checked ~ .title3 {
    background: rgba(255, 0, 0, 0.5);
}

#tab4:checked ~ .title4 {
  background: rgba(0, 140, 255, 0.5);
}

#tab1:checked ~ #body1,
#tab2:checked ~ #body2,
#tab3:checked ~ #body3,
#tab4:checked ~ #body4{
  display: block;
}

form{
    text-align: center;
}



.kiroku{
  width: 100px;
  margin-top: 10px;
}


<main>
    <div class="bb">
        <button class="button" onclick="location.href='renrakutyou.html'">連絡帳を書く</button>
    </div>

    <div class="container add-control">
        <input type="checkbox" id="tab1" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title1" id="title1" for="tab1">睡眠</label>
        <input type="checkbox" id="tab2" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title2" id="title2" for="tab2">ごはん</label>
        <input type="checkbox" id="tab3" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title3" id="title3" for="tab3">トイレ</label>
        <input type="checkbox" id="tab4" class="radio" name="tab" onclick="onlyOne(this)"><label class="tab-title title4" id="title4" for="tab4">くすり</label>

        <div class="tab-body" id="body1">
            <form action="">
                <table>
                    <tr>
                        <td><label><input type="radio" name="sleep_amount" required>寝た</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="sleep_amount" required>起きた</label></td>
                    </tr>
                </table>
                <button class="kiroku" name="sleep_btn">記録</button>
            </form>
        </div>

        <div class='tab-body' id="body2">
            <form action="">
                <table>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" required>ぜんぶ</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" required>はんぶん</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="meal_amount" required>すこし</label></td>
                    </tr>
                </table>
                <button class="kiroku" name="meal_btn">記録</button>
            </form>
        </div>

        <div class="tab-body" id="body3">
            <form action="">
                <table>
                    <tr>
                        <td><label><input type="checkbox" name="excretion_amount">かたい</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="checkbox" name="excretion_amount">やわらかい</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="option" id="radio1">その他</label></td>
                    </tr>
                </table>    
                <input type="text" oninput="autoCheck(this.value, 'radio1')">
                <br>    
                <button class="kiroku" name="excretion_btn">記録</button>
            </form>
        </div>

        <div class="tab-body" id="body4">
            <form action="">
                <!-- <table>
                    <tr>
                        <td><label><input type="radio" name="sleep_amount" required>寝た</label></td>
                    </tr>
                    <tr>
                        <td><label><input type="radio" name="sleep_amount" required>起きた</label></td>
                    </tr>
                </table> -->
                <button class="kiroku" name="sleep_btn">記録</button>
            </form>
        </div>

    </div>

   </main>