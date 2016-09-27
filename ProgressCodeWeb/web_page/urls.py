from django.conf.urls import include, url
from . import views

urlpatterns = [
	url(r'^$', views.index),
	url(r'^form/$', views.forms),
	url(r'^form/csv/$', views.csv_reader),
	url(r'^form/event/$', views.evento_form),
	url(r'^form/tutor/$', views.tutor_form),
	url(r'^form/encontro/$', views.encontro_form)
]
