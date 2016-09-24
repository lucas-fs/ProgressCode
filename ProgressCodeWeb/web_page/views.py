from django.shortcuts import render
import csv

from web_service.models import Inscrito

def index(request):
	username = passwd = ''
	if request.POST:
			username = request.POST.get('username')
			passwd = request.POST.get('passwd')
	return render(request, 'web_page/index.html', locals())

def csv_reader(request):
	tabela = []
	if request.POST and request.FILES:
		csvFile = request.FILES['csv_file']
		for line in csvFile:
			tabela.append(line.decode("utf-8")[:-1].split(","))
		for i in range(len(tabela)):
			Inscrito.objects.create(nome=tabela[i][0], data_nasc=tabela[i][1], escola=tabela[i][2])
	return render(request, 'web_service/csv_reader.html', locals())
