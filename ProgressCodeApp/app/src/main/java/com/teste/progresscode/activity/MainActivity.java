package com.teste.progresscode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.teste.progresscode.R;
import com.teste.progresscode.fragment.HomeFragment;
import com.teste.progresscode.fragment.MoviesFragment;
import com.teste.progresscode.fragment.NotificationsFragment;
import com.teste.progresscode.fragment.PhotosFragment;
import com.teste.progresscode.fragment.SettingsFragment;
import com.teste.progresscode.other.CircleTransform;

/**
 * Create by Lucas Ferreira da Silva
 * */

public class MainActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    // Urls para download das imagens do header do menu drawer
    private static final String urlNavHeaderBg = "http://wallup.net/wp-content/uploads/2016/01/161688-minimalism-pattern-abstract-lines-geometry-300x200.jpg";
    private static final String urlProfileImg = "https://scontent.fpoa3-1.fna.fbcdn.net/v/t1.0-9/13237857_801484059983097_2358801116926512026_n.jpg?oh=afc36f2d4be5d7ebfa3f8aae09a85882&oe=586446BD";

    // Indice do item atual do menu
    public static int navItemIndex = 0;

    // Tags usadas para anexar (attach) um fragment
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;

    // Titulos do toolbar com os respectivos itens selecionados no menu
    private String[] activityTitles;

    // Flag para carregar o fragment home quando o usuario preciona o botão de voltar
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        // Header do navigation view
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) navHeader.findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);

        // Carrega os titulos do toolbar de resources string
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // Seta o listener do FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Carrega os dados do header do menu drawer
        loadNavHeader();

        // Inicializa o menu drawer
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    /*** Carrega informacoes do menu drawer como:
     * background, imagem de perfil, nome, website(email),
     * notifications action view (ponto)
     */

    private void loadNavHeader() {

        // nome, website
        txtName.setText("Lucas Ferreira da Silva");
        txtWebsite.setText("lferreira@inf.ufsm.br");

        // Carrega a imagem de background do header
        Glide.with(this).load(urlNavHeaderBg)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgNavHeaderBg);

        // Carrega a imagem de perfil do header
        Glide.with(this).load(urlProfileImg)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

        // Mostra o ponto de proximo no label notifications
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
    }

    /***
     * Retorna o respectivo fragment que o
     * usuario selecionou no menu drawer
     */
    private void loadHomeFragment() {

        // selecting appropriate nav menu item
        selectNavMenu();

        // Seta o titulo do toolbar
        setToolbarTitle();

        // Se o usuario selecionar o navigation menu atual denovo, nao faz nada apenas fecha o menu
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            // Mostra ou oculta o fab
            toggleFab();
            return;
        }

        /***
         * As vezes, quando fragmento tem dados muito grandes, a tela parece
         * "pendurada" ao alternar entre menus de navegação.
         * Então, usando runnable, o fragmento é carregado com
         * efeito de cross fade
         * Este efeito pode ser visto no app do GMail
         */

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // Se mPendingRunnable nao e null, entao adiciona a mensagem na fila
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // Mostra ou oculta o fab
        toggleFab();

        // Fecha o menu se um item foi selecionado
        drawer.closeDrawers();

        // Refresh toolbar menu
        invalidateOptionsMenu();
    }

    // Retorna o fragment correspondente ao item selecionado
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            case 1:
                // photos
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            case 2:
                // movies fragment
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;
            case 3:
                // notifications fragment
                NotificationsFragment notificationsFragment = new NotificationsFragment();
                return notificationsFragment;

            case 4:
                // settings fragment
                SettingsFragment settingsFragment = new SettingsFragment();
                return settingsFragment;
            default:
                return new HomeFragment();
        }
    }

    // Seta o titulo do toolbar conforme o item selecionado no menu
    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    // Troca o estado do item selecionado no menu para "checked"
    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Seta o Navigation View Item Selected Listener para tratar o click no menu drawer
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // Este metodo sera disparado quando um item for selecionado no menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                // Testa qual item estava sendo clicado e executa a ação apropriada
                switch (menuItem.getItemId()) {

                    // Substitui o main content com o ContentFragment
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_photos:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;

                    case R.id.nav_movies:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;

                    case R.id.nav_notifications:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;

                    case R.id.nav_settings:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;

                    case R.id.nav_about_us:
                        // Inicia uma nova activity ao inves de um fragment
                        startActivity(new Intent(MainActivity.this, AboutUsActivity.class));
                        drawer.closeDrawers();
                        return true;

                    case R.id.nav_privacy_policy:
                        // Inicia uma nova activity ao inves de um fragment
                        startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
                        drawer.closeDrawers();
                        return true;

                    default:
                        navItemIndex = 0;
                }

                // Testa se o item esta no estado selecionado ou nao, se não faz ele ficar no estado de selecionado
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Metodo disparado quando o menu e fechado
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Metodo disparado quando o menu e aberto
                super.onDrawerOpened(drawerView);
            }
        };

        // Seta o actionbarToggle para o drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        // Chamar o estado de sincronizacao e necessario senao o icone nao ira aparecer
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // Este codigo carrega o fragment home quando o boto de voltar e precionado
        // Quando o usuario estiver em outro fragmento a nao ser o home
        if (shouldLoadHomeFragOnBackPress) {
            // Verifica se o usuario esta em outro item do menu a nao ser o home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infla o menu; isto acrescenta itens ao action bar se ele estiver presente.

        // Mostra o menu somente quando o fragment home for selecionado
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.main, menu);
        }

        // Quando o fragment for notifications carrega o menu criado para notifications
        if (navItemIndex == 3) {
            getMenuInflater().inflate(R.menu.notifications, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Trata os item do action bar clicados. O action bar vai tratar automaticamente os clicks no botao Home/Up,
        // contanto que se especifique uma atividade pai no AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }

        // O usuario esta no fragment notifications e seleciona 'Mark all as Read'
        if (id == R.id.action_mark_all_read) {
            Toast.makeText(getApplicationContext(), "All notifications marked as read!", Toast.LENGTH_LONG).show();
        }

        // O usuario esta no fragment notifications e seleciona 'Clear All'
        if (id == R.id.action_clear_notifications) {
            Toast.makeText(getApplicationContext(), "Clear all notifications!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Mostra ou oculta o fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
}