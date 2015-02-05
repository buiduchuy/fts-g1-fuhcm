<%-- 
    Document   : tao-hang-1
    Created on : Jan 30, 2015, 11:21:10 AM
    Author     : KhuongNguyen-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<title>Tạo hàng</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
<c:set var="today" value="<%=new Date()%>" />
<c:set var="tomorrow"
	value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>" />
<jsp:include page="header.jsp" />
<script src="js/foundation-datepicker.js"></script>
<link rel="stylesheet" href="css/foundation-datepicker.css">
<section class="container">
	<center>
		<div class="form-content"
			style="border: 1px solid #ccc; box-shadow: 1px 1px 2px 2px #CCC; margin-bottom: 50px; width: 800px;">

			<div class="row"></div>
			<div class="large-12 columns">
				<h1 class="page-title">
					<font color="orange">Tạo hàng</font>
				</h1>
				<ul class="button-group even-4">
					<li><a href="#" class="button ">Địa chỉ giao nhận hàng</a></li>
					<li><a class="button disabled">Thông tin hàng hoá</a></li>
					<li><a class="button disabled">Chi phí</a></li>
					<li><a class="button disabled">Xác nhận</a></li>
				</ul>
			</div>


			<form action="Controller" method="get" accept-charset="utf-8">
				<div class="row">
					<div class="large-12 columns">
						<div class="row">
							<div class="extra-title">
								<h3>Địa chỉ giao hàng</h3>
							</div>
							<div class="row">
								<div class="small-8 columns">
									<div class="small-3 columns">
										<label class="right inline"><small class="validate">*</small>
											Địa chỉ: </label>
									</div>
									<div class="small-9 columns">
										<input class="left inline" type="text" onFocus="geolocate()"
											id="place_start" pattern=".{1,100}"
											placeholder="Nhập địa điểm giao hàng" required=""
											data-errormessage-value-missing="Vui lòng chọn địa điểm giao hàng !"
											data-errormessage-pattern-mismatch="Bạn phải nhập địa chỉ [1-50] kí tự !" />
									</div>
								</div>
								<div class="small-4 columns">
									<div class="small-5 columns">
										<label for="right-label" class="right inline"><small
											class="validate">*</small> Ngày: </label>
									</div>
									<div class="small-7 columns">
										<input type="text"
											value="<fmt:formatDate type="date" value='${today}' pattern='dd-MM-yyyy'/> "
											id="pick-up-date" data-date-format="dd-mm-yyyy" readonly>
									</div>
								</div>
							</div>

							<div class="extra-title">
								<h3>Địa chỉ nhận hàng</h3>
							</div>
							<div class="row">
								<div class="small-8 columns">
									<div class="small-3 columns">
										<label for="right-label" class="right inline"><small
											class="validate">*</small> Địa chỉ: </label>
									</div>
									<div class="small-9 columns">
										<input type="text" onFocus="geolocate()" id="place_end"
											pattern=".{1,100}" placeholder="Nhập địa điểm nhận hàng"
											required=""
											data-errormessage-value-missing="Vui lòng chọn địa điểm nhận hàng !"
											data-errormessage-pattern-mismatch="Bạn phải nhập địa chỉ [1-100] kí tự !" />
									</div>
								</div>
								<div class="small-4 columns">
									<div class="small-5 columns">
										<label for="right-label" class="right inline"><small
											class="validate">*</small> Ngày: </label>
									</div>
									<div class="small-7 columns">
										<input type="text"
											value="<fmt:formatDate type="date" value='${tomorrow}' pattern='dd-MM-yyyy'/>"
											id="dilivery-date" data-date-format="dd-mm-yyyy" readonly>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="large-12 columns">
									<div class="submit-area right">
										<button class="success" name="btnAction" value="next1">
											<i class="icon-mail-forward"></i> Tiếp theo
										</button>

									</div>
									</br>
								</div>
							</div>
							<div class="row"></div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</center>
</section>

<!-- autocomplete place google API -->
<script> src = "https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places" ></script>
<script>
            // Script autocomplete place for location pick up and location delivery
            // of the Google Places API to help users fill in the information.

            var placeSearch, place_start, place_end
    var options = {
        types: ['geocode'],
        componentRestrictions: {country: "vn"}
    };
    function auto() {
        place_start = new google.maps.places.Autocomplete(
                (document.getElementById('place_start')), options);
        place_end = new google.maps.places.Autocomplete(
                (document.getElementById('place_end')), options);
    }

    function geolocate() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var geolocation = new google.maps.LatLng(
                        position.coords.latitude, position.coords.longitude);
                var circle = new google.maps.Circle({
                    center: geolocation,
                    radius: position.coords.accuracy
                });
                autocomplete.setBounds(circle.getBounds());
            });
        }
    }
</script>
<!-- end -->

<script>
            $(function () {
                window.prettyPrint && prettyPrint();
                $('#d-pick-up-date').fdatepicker({
                });
                $('#d-dilivery-date').fdatepicker({
                });
                var nowTemp = new Date();
                var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
                var checkin = $('#pick-up-date').fdatepicker({
                    onRender: function (date) {
                        return date.valueOf() < now.valueOf() ? 'disabled' : '';
                    }
                }).on('changeDate', function (ev) {
                    if (ev.date.valueOf() > checkout.date.valueOf()) {
                        var newDate = new Date(ev.date)
                        newDate.setDate(newDate.getDate() + 1);
                        checkout.update(newDate);
                    }
                    checkin.hide();
                 
                }).data('datepicker');
                var checkout = $('#dilivery-date').fdatepicker({
                    onRender: function (date) {
                        return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
                    }
                }).on('changeDate', function (ev) {
                    checkout.hide();
                }).data('datepicker');
            });
        </script>
<jsp:include page="footer.jsp" />
