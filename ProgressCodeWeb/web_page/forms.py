from django import forms
from django.contrib import admin
from django.conf import settings
from web_service.models import *

class RegistrationForm(forms.ModelForm):
	nome = forms.CharField(label="Nome")
	email = forms.EmailField(label="Email")
	password1 = forms.CharField(widget=forms.PasswordInput,label="Password")
	password2 = forms.CharField(widget=forms.PasswordInput,label="Password (again)")

	class Meta:
		model = Tutor
		fields = ['nome', 'email', 'password1', 'password2']

	def clean(self):
		cleaned_data = super(RegistrationForm, self).clean()
		if 'password1' in self.cleaned_data and 'password2' in self.cleaned_data:
			if self.cleaned_data['password1'] != self.cleaned_data['password2']:
				raise forms.ValidationError("Passwords don't match. Please enter both fields again.")
		return self.cleaned_data

	def save(self, commit=True):
		tutor = super(RegistrationForm, self).save(commit=False)
		tutor.set_password(self.cleaned_data['password1'])
		if commit:
			tutor.save()
		return tutor

	def __init__(self, *args, **kwargs):
		super(RegistrationForm, self).__init__(*args, **kwargs)
		self.fields['nome'].widget.attrs['class']="form-control"
		self.fields['email'].widget.attrs['class']="form-control"
		self.fields['password1'].widget.attrs['class']="form-control"
		self.fields['password2'].widget.attrs['class']="form-control"

class AuthenticationForm(forms.Form):
    email = forms.EmailField(widget=forms.TextInput)
    password = forms.CharField(widget=forms.PasswordInput)

    class Meta:
        fields = ['email', 'password']

class EventoForm(forms.ModelForm):
	class Meta:
		model = Evento
		fields = ['nome_evento', 'descricao']

class EquipeForm(forms.ModelForm):
	class Meta:
		model = Equipe
		fields = ['descricao']
'''
class TutorForm(forms.ModelForm):
	class Meta:
		model = Tutor
		fields = ['nome', 'email', 'senha']
'''
class InscritoForm(forms.ModelForm):
	class Meta:
		model = Inscrito
		fields = ['nome', 'data_nasc', 'escola']
		widgets = {
			'data_nasc' : forms.DateInput(format=settings.DATE_INPUT_FORMATS)
		}

class EncontroForm(forms.ModelForm):
	class Meta:
		model = Encontro
		fields = ['data_realizao', 'evento']
		widgets = {
			'data_realizao' : forms.SelectDateWidget(empty_label="Nothing")
		}

class FeedbackForm(forms.ModelForm):
	class Meta:
		model = Feedback
		fields = '__all__'

class AtividadeForm(forms.ModelForm):
	class Meta:
		model = Atividade
		fields = ['descricao', 'encontro']
