from django.conf.urls import include, url
from . import views

urlpatterns = [
	url(r'^$', views.login),
	url(r'^logout/$', views.logout),
	url(r'^register/$', views.register, name='register'),
	url(r'^form/$', views.forms),
	url(r'^form/csv/$', views.csv_reader),
	url(r'^form/evento/$', views.evento_form),
	url(r'^form/tutor/$', views.tutor_form),
	url(r'^form/equipe/$', views.equipe_form),
	url(r'^form/inscrito/$', views.inscrito_form),
	url(r'^form/encontro/$', views.encontro_form),
	url(r'^form/feedback/$', views.feedback_form),
	url(r'^form/atividade/$', views.atividade_form),


	url(r'^form/choiceInsc/$', views.choiceInsc),

	url(r'^list/inscritos/$', views.inscritos_list),
	url(r'^list/feedbacks/$', views.feedbacks_list),
	url(r'^list/tutores/$', views.tutores_list),
	url(r'^list/eventos/$', views.eventos_list),
	url(r'^list/encontros/$', views.encontros_list),
	url(r'^list/atividades/$', views.atividades_list),
	url(r'^list/insc_feed/$', views.inscFeed_list)
]
