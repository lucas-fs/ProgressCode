from django import forms
from django.contrib import admin
from web_service.models import *

class EventoForm(forms.ModelForm):
	class Meta:
		model = Evento
		fields = ('nome_evento', 'descricao')

class TutorForm(forms.ModelForm):
	class Meta:
		model = Tutor
		fields = ('nome', 'email', 'senha')

class EncontroForm(forms.ModelForm):
	class Meta:
		model = Encontro
		fields = "__all__"
	def __init__(self, *args, **kwargs):
		super(EncontroForm, self).__init__(*args, **kwargs)
		if self.instance:
			self.fields['evento'].queryset = Evento.objects.all()
			self.fields['data_realizao'] = forms.CharField()

