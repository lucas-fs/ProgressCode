from django.shortcuts import render

def index(request):
	username = passwd = ''
	if request.POST:
			username = request.POST.get('username')
			passwd = request.POST.get('passwd')
	return render(request, 'web_page/index.html', locals())