package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PublicacionAdapter adapter;
    private List<Publicacion> listaPublicaciones;
    private static final int FILTER_REQUEST_CODE = 1; // Código para identificar la solicitud de filtro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializa la lista de publicaciones
        listaPublicaciones = new ArrayList<>();
// Aquí debes agregar tus publicaciones, por ejemplo:

        listaPublicaciones.add(new Publicacion("Hotel Casa Azul",
                "Located in Mérida, Yucatán",
                "A stunning boutique hotel situated in a beautifully restored colonial mansion, Hotel Casa Azul offers luxurious accommodations with a touch of local charm. Guests can enjoy elegantly designed rooms, an outdoor pool, and exceptional service that makes every stay memorable.",
                "The hotel features exquisite decor and provides a unique ambiance that combines modern comforts with historical elegance. Guests rave about the warm hospitality and attention to detail, making it a perfect getaway for couples seeking romance and relaxation.",
                R.drawable.casa_azul,
                "$2,500 MX - $5,000 MX", "Couples", "Cultural"));

        listaPublicaciones.add(new Publicacion("La Isla Shopping Village",
                "Located in Cancun, Quintana Roo",
                "La Isla Shopping Village is a vibrant outdoor shopping center that features a wide variety of international brands and local artisans. With its picturesque canals and scenic views, it provides an enjoyable shopping experience amidst beautiful surroundings.",
                "Visitors love the great variety of shops, restaurants, and entertainment options available. It’s a perfect spot for families looking to spend a day shopping while enjoying the unique atmosphere and delicious dining options.",
                R.drawable.la_isla,
                "$1,000 MX - $2,500 MX", "Families", "Shopping"));

        listaPublicaciones.add(new Publicacion("Cabo Adventures: Camel Ride",
                "Located in Cabo San Lucas, Baja California Sur",
                "Experience a thrilling adventure riding camels through the stunning desert landscape of Cabo San Lucas. This unique tour combines exhilarating camel rides with breathtaking ocean views, creating an unforgettable experience for adventurers.",
                "Guests are often amazed by the friendly camels and the knowledgeable guides who share interesting facts about the local flora and fauna. It’s an exciting activity for large groups looking for adventure and fun in the sun.",
                R.drawable.cabo_adventures,
                "$1,000 MX - $2,500 MX", "Large groups (5+ people)", "Adventure"));

        listaPublicaciones.add(new Publicacion("Xcaret Park",
                "Located in Playa del Carmen, Quintana Roo",
                "Xcaret Park is an eco-archaeological wonder that offers visitors a wide range of cultural, natural, and aquatic attractions. From swimming in underground rivers to exploring archaeological sites, there's something for everyone to enjoy.",
                "With incredible variety and immersive experiences, families will love spending the day at this park. The shows and performances highlight Mexican culture and traditions, ensuring a fun and educational experience for all ages.",
                R.drawable.xcaret,
                "$2,500 MX - $5,000 MX", "Families", "Family-Friendly"));

        listaPublicaciones.add(new Publicacion("Mayan Ruins of Tulum",
                "Located in Tulum, Quintana Roo",
                "Visit the stunning Mayan ruins of Tulum, perched on cliffs overlooking the Caribbean Sea. This well-preserved archaeological site offers a glimpse into the ancient Mayan civilization and its breathtaking coastal views.",
                "Visitors often describe the experience as awe-inspiring, with the ancient structures set against the backdrop of the turquoise sea. It’s a must-see for solo travelers and history enthusiasts alike.",
                R.drawable.tulum_ruins,
                "$500 MX - $1,000 MX", "Solo traveler", "Historical"));

        listaPublicaciones.add(new Publicacion("Temazcal Wellness Spa",
                "Located in Oaxaca, Oaxaca",
                "Immerse yourself in the traditional Mexican temazcal ceremony, a steam bath experience designed for relaxation and healing. This ancient practice offers a unique way to unwind and rejuvenate the body and mind.",
                "Guests rave about the soothing atmosphere and the personalized attention provided by the staff, making it an ideal retreat for couples looking to reconnect and rejuvenate together.",
                R.drawable.temazcal_spa,
                "$1,000 MX - $2,500 MX", "Couples", "Wellness and Spa"));

        listaPublicaciones.add(new Publicacion("Puerto Vallarta Malecon",
                "Located in Puerto Vallarta, Jalisco",
                "The Malecon is a scenic boardwalk that runs along the coast of Puerto Vallarta, lined with stunning sculptures, shops, and restaurants. It’s a vibrant place to enjoy a stroll, watch street performers, and soak in the local culture.",
                "Visitors appreciate the lively atmosphere, especially in the evenings when the area comes alive with entertainment. It’s a fantastic spot for large groups looking to enjoy nightlife and explore the beauty of Puerto Vallarta.",
                R.drawable.pv_malecon,
                "$500 MX - $1,000 MX", "Large groups (5+ people)", "Nightlife"));

        listaPublicaciones.add(new Publicacion("Tequila Factory Tour",
                "Located in Tequila, Jalisco",
                "Embark on a guided tour of a traditional tequila distillery, where you’ll learn about the production process and the rich history of tequila-making in Mexico. Enjoy tastings and cultural insights throughout the tour.",
                "Tour participants love the in-depth knowledge shared by the guides, who offer a fascinating look into the world of tequila. It's a perfect outing for couples interested in cultural experiences and, of course, great tequila.",
                R.drawable.tequila_tour,
                "$1,000 MX - $2,500 MX", "Couples", "Cultural"));

        listaPublicaciones.add(new Publicacion("Hotel Rosewood San Miguel de Allende",
                "Located in San Miguel de Allende, Guanajuato",
                "Hotel Rosewood is a luxury destination that boasts stunning rooftop views and upscale amenities. This exquisite hotel offers a perfect blend of comfort and elegance, making it a favorite among discerning travelers.",
                "Guests rave about the exceptional service, beautifully designed rooms, and tranquil atmosphere. It’s an ideal choice for couples seeking a romantic getaway filled with relaxation and sophistication.",
                R.drawable.rosewood_sma,
                "$2,500 MX - $5,000 MX", "Couples", "Relaxation"));

        listaPublicaciones.add(new Publicacion("Frida Kahlo Museum",
                "Located in Mexico City, CDMX",
                "Explore the iconic Blue House, once home to the famous artist Frida Kahlo. The museum showcases her life, art, and personal belongings, offering an intimate glimpse into her creative world.",
                "Visitors often find the experience very inspiring, with many expressing admiration for Kahlo’s artistry and resilience. It’s a must-visit for solo travelers and art enthusiasts who appreciate cultural landmarks.",
                R.drawable.frida_museum,
                "$500 MX - $1,000 MX", "Solo traveler", "Historical"));

        listaPublicaciones.add(new Publicacion("La Quebrada Cliff Divers",
                "Located in Acapulco, Guerrero",
                "Experience the thrill of watching daring divers leap from towering cliffs into the ocean at La Quebrada. This breathtaking display of skill and courage is a highlight for anyone visiting Acapulco.",
                "Many visitors describe the show as thrilling and unforgettable. It’s an ideal activity for large groups looking for excitement and a unique view of Acapulco’s vibrant culture.",
                R.drawable.la_quebrada,
                "$500 MX - $1,000 MX", "Large groups (5+ people)", "Adventure"));

        listaPublicaciones.add(new Publicacion("San Cristobal Market",
                "Located in San Cristobal de las Casas, Chiapas",
                "The lively San Cristobal Market is filled with colorful stalls offering handmade crafts, local produce, and delicious food. It’s a fantastic place to experience the local culture and pick up unique souvenirs.",
                "Visitors often find the atmosphere charming, with friendly vendors and a wide variety of products. It’s a great destination for families wanting to explore and enjoy shopping together.",
                R.drawable.san_cristobal_market,
                "$500 MX - $1,000 MX", "Families", "Shopping"));

        listaPublicaciones.add(new Publicacion("Hotel Xcaret Mexico",
                "Located in Riviera Maya, Quintana Roo",
                "Hotel Xcaret Mexico is a luxury all-inclusive resort that combines eco-friendly amenities with access to stunning parks and attractions. This unique destination offers guests an unparalleled experience surrounded by nature.",
                "Guests frequently praise the top-notch facilities and diverse activities available at the resort. It’s an excellent choice for families looking to enjoy a fun-filled vacation with plenty of options for entertainment.",
                R.drawable.hotel_xcaret,
                "$2,500 MX - $5,000 MX", "Families", "Family-Friendly"));

        listaPublicaciones.add(new Publicacion("Hacienda Tres Ríos",
                "Located in Playa del Carmen, Quintana Roo",
                "Hacienda Tres Ríos is an eco-resort that offers a perfect blend of adventure, relaxation, and cultural experiences. Nestled in a lush natural environment, it’s an ideal getaway for those looking to connect with nature.",
                "Guests appreciate the wonderful stay, enjoying the activities available on-site as well as the serene environment. It’s a fantastic option for families seeking adventure and relaxation at the same time.",
                R.drawable.hacienda_tres_rios,
                "$2,500 MX - $5,000 MX", "Families", "Adventure"));

        listaPublicaciones.add(new Publicacion("Parque Fundidora",
                "Located in Monterrey, Nuevo León",
                "Parque Fundidora is a former steel foundry transformed into a large urban park filled with museums, art installations, and recreational areas. It’s a great place for families to explore and enjoy outdoor activities.",
                "Many families find it a great spot for picnics, walks, and cultural experiences. The park offers a perfect blend of history and modernity, making it a unique destination in Monterrey.",
                R.drawable.parque_fundidora,
                "$500 MX - $1,000 MX", "Families", "Family-Friendly"));

        listaPublicaciones.add(new Publicacion("Chichen Itza: Ancient Maya Ruins",
                "Located in Yucatán Peninsula",
                "Chichen Itza is one of the Seven Wonders of the World and a UNESCO World Heritage Site. This ancient Maya city offers a fascinating insight into the rich history and culture of the Mayan civilization.",
                "Visitors describe the experience as majestic, with many expressing their awe at the impressive architecture and historical significance. It's a must-visit for couples and history lovers looking to explore ancient ruins.",
                R.drawable.downloadchichen_itza,
                "$1,000 MX - $2,500 MX", "Couples", "Historical"));

        listaPublicaciones.add(new Publicacion("Cabo San Lucas Resort",
                "Located in Baja California Sur",
                "Cabo San Lucas Resort is a luxury beachfront property offering a perfect combination of relaxation and adventure. With stunning views and exceptional amenities, it’s an ideal retreat for families and couples alike.",
                "Guests are often impressed by the amazing service and facilities available at the resort, making it a top choice for families seeking a memorable vacation experience.",
                R.drawable.cabo_san_lucas_resort,
                "$2,500 MX - $5,000 MX", "Families", "Relaxation"));

        listaPublicaciones.add(new Publicacion("Tulum Beach Yoga Retreat",
                "Located in Tulum, Quintana Roo",
                "Escape to a serene yoga retreat by the beach in Tulum, where you can find peace and wellness surrounded by nature. This retreat offers daily yoga sessions and holistic wellness activities.",
                "Guests describe the experience as peaceful and rejuvenating, making it a perfect getaway for solo travelers seeking relaxation and self-discovery.",
                R.drawable.tulum_beach_yoga,
                "$2,500 MX - $5,000 MX", "Solo traveler", "Wellness and Spa"));

        listaPublicaciones.add(new Publicacion("Xochimilco Boat Ride",
                "Located in Mexico City",
                "Enjoy colorful boat rides through the floating gardens of Xochimilco, a UNESCO World Heritage site. This vibrant experience offers a unique way to explore the beautiful canals and enjoy local food and music.",
                "Couples and families love the vibrant atmosphere and the opportunity to enjoy a leisurely ride while immersing themselves in the local culture. It’s a fun activity for all ages.",
                R.drawable.xochimilco_boat_ride,
                "$500 MX - $1,000 MX", "Couples", "Family-Friendly"));

        listaPublicaciones.add(new Publicacion("La Condesa Shopping Tour",
                "Located in Mexico City",
                "The trendy neighborhood of La Condesa is known for its stylish boutiques, unique shops, and chic cafes. This shopping tour takes you through the best spots to find fashionable items and souvenirs.",
                "Solo travelers often find this area chic and vibrant, making it a delightful experience for those looking to shop and enjoy the local atmosphere.",
                R.drawable.condesa_shopping,
                "$1,000 MX - $2,500 MX", "Solo traveler", "Shopping"));

        listaPublicaciones.add(new Publicacion("Guadalajara Cultural Walking Tour",
                "Located in Guadalajara, Jalisco",
                "Explore the rich culture and stunning architecture of Guadalajara on a guided walking tour. This immersive experience takes you through historical sites, markets, and cultural landmarks.",
                "Many couples describe the tour as insightful, gaining a deeper appreciation for the city’s heritage. It’s an excellent opportunity for those interested in cultural experiences.",
                R.drawable.guadalajara_walking_tour,
                "$500 MX - $1,000 MX", "Couples", "Cultural"));

        listaPublicaciones.add(new Publicacion("Copper Canyon Train Adventure",
                "Located in Chihuahua",
                "Embark on a breathtaking train journey through Copper Canyon, one of the largest canyons in the world. This scenic route offers stunning views and an unforgettable experience.",
                "Families often find the adventure thrilling, as the train winds through beautiful landscapes, making it a great way to bond and create lasting memories together.",
                R.drawable.copper_canyon_train,
                "$1,000 MX - $2,500 MX", "Families", "Adventure"));

        listaPublicaciones.add(new Publicacion("Cancún Nightlife Tour",
                "Located in Cancún, Quintana Roo",
                "Experience the vibrant nightlife scene of Cancún with a guided tour of the best clubs, bars, and shows. Enjoy a night full of excitement and entertainment.",
                "Large groups love the thrilling atmosphere and variety of options available for nightlife in Cancún, making it a perfect outing for those seeking fun and adventure.",
                R.drawable.cancun_nightlife,
                "$1,000 MX - $2,500 MX", "Large groups (5+ people)", "Nightlife"));

        listaPublicaciones.add(new Publicacion("Cancún Resort and Waterpark",
                "Located in Cancún, Quintana Roo",
                "A family-friendly resort featuring a waterpark with thrilling attractions and activities. This resort offers an exciting vacation experience for guests of all ages.",
                "Many families find the resort super fun, with plenty of options for entertainment and relaxation, making it a top choice for large groups looking for a memorable getaway.",
                R.drawable.cancun_resort,
                "$2,500 MX - $5,000 MX", "Large groups (5+ people)", "Family-Friendly"));

        listaPublicaciones.add(new Publicacion("Laguna de los Siete Colores",
                "Located in Bacalar, Quintana Roo",
                "La Laguna de los Siete Colores is a stunning natural wonder famous for its various shades of blue. This lagoon offers a serene escape for those looking to enjoy water activities like kayaking, swimming, or simply relaxing by the shore.",
                "Visitors often describe the beauty of the lagoon as breathtaking, making it a perfect destination for couples seeking tranquility and families looking for a fun day in nature.",
                R.drawable.bacalar_lagoon,
                "$500 MX - $1,000 MX", "Couples", "Adventure"));

        listaPublicaciones.add(new Publicacion("Cozumel Snorkeling Tour",
                "Located in Cozumel, Quintana Roo",
                "Join a guided snorkeling tour in Cozumel, known for its vibrant coral reefs and diverse marine life. This adventure offers a fantastic opportunity to explore the underwater world.",
                "Many guests praise the knowledgeable guides and the incredible sights they encounter. It's a must-do for families looking for exciting outdoor activities and unforgettable experiences.",
                R.drawable.cozumel_snorkeling,
                "$1,000 MX - $2,500 MX", "Families", "Adventure"));

        listaPublicaciones.add(new Publicacion("Palenque Ruins",
                "Located in Chiapas",
                "Palenque is an archaeological site surrounded by lush jungle, showcasing the remnants of the ancient Maya civilization. Explore the impressive temples and learn about the rich history of this UNESCO World Heritage site.",
                "Visitors find the site mesmerizing, with many noting the tranquility of the jungle setting. It’s an excellent destination for solo travelers and history buffs eager to uncover the mysteries of the Mayans.",
                R.drawable.palenque,
                "$500 MX - $1,000 MX", "Solo traveler", "Historical"));

        listaPublicaciones.add(new Publicacion("Cenote Ik Kil",
                "Located in Yucatán",
                "Cenote Ik Kil is a breathtaking natural sinkhole filled with crystal-clear waters. This popular cenote offers visitors a chance to swim and relax in a stunning environment surrounded by lush vegetation.",
                "Many families enjoy spending the day here, appreciating the beauty and tranquility of the cenote. It’s a fantastic spot for those looking to connect with nature and have fun together.",
                R.drawable.ik_kil,
                "$500 MX - $1,000 MX", "Families", "Adventure"));

        listaPublicaciones.add(new Publicacion("San Miguel de Allende Art Walk",
                "Located in San Miguel de Allende, Guanajuato",
                "Experience the vibrant art scene of San Miguel de Allende with a guided art walk through its colorful streets. This tour takes you to various galleries and studios, showcasing local artists and their work.",
                "Visitors often describe the experience as inspiring, filled with creativity and culture. It’s an excellent outing for couples and solo travelers who appreciate art and local craftsmanship.",
                R.drawable.art_walk_sma,
                "$500 MX - $1,000 MX", "Couples", "Cultural"));

        listaPublicaciones.add(new Publicacion("Taco Tasting Tour",
                "Located in Mexico City",
                "Embark on a delicious taco tasting tour through the bustling streets of Mexico City. Sample a variety of tacos from local vendors and learn about their history and preparation.",
                "Participants rave about the incredible flavors and the opportunity to explore the city through its culinary delights. It’s a fun and flavorful adventure for families and solo travelers alike.",
                R.drawable.taco_tour,
                "$500 MX - $1,000 MX", "Families", "Culinary"));

        listaPublicaciones.add(new Publicacion("Catedral Metropolitana",
                "Located in Mexico City",
                "The Catedral Metropolitana is a stunning architectural marvel situated in the heart of Mexico City. This historic cathedral showcases beautiful artwork and intricate designs, making it a must-visit for any traveler.",
                "Many visitors find the experience awe-inspiring, as they explore the rich history and stunning interiors. It’s an essential stop for solo travelers and couples interested in culture and history.",
                R.drawable.catedral_metropolitana,
                "$0 MX - $500 MX", "Solo traveler", "Historical"));

        listaPublicaciones.add(new Publicacion("Quintana Roo Beach Resort",
                "Located in Tulum, Quintana Roo",
                "This luxurious beach resort offers a perfect escape with stunning ocean views and exceptional service. Guests can enjoy various amenities, including fine dining, spa services, and water sports.",
                "Visitors often highlight the relaxing atmosphere and beautiful surroundings, making it an ideal choice for couples seeking a romantic getaway or families wanting a fun-filled vacation.",
                R.drawable.tulum_resort,
                "$2,500 MX - $5,000 MX", "Couples", "Relaxation"));

        listaPublicaciones.add(new Publicacion("Chichén Itzá Light Show",
                "Located in Yucatán",
                "Experience the ancient Mayan civilization come to life at the Chichén Itzá Light Show, which showcases the history and legends of this UNESCO World Heritage site through stunning visuals and music.",
                "Visitors often describe the show as captivating and educational, making it a fantastic evening activity for families and couples looking to deepen their understanding of Mayan culture.",
                R.drawable.chichen_light_show,
                "$500 MX - $1,000 MX", "Families", "Cultural"));

        listaPublicaciones.add(new Publicacion("Callejón del Beso",
                "Located in Guanajuato, Guanajuato",
                "Callejón del Beso is a romantic alley known for its charming ambiance and the legend of star-crossed lovers. It’s a popular spot for couples to visit and take memorable photos.",
                "Many couples express their delight at the enchanting atmosphere, making it a perfect stop for those seeking romance in the picturesque streets of Guanajuato.",
                R.drawable.callejon_beso,
                "$0 MX - $500 MX", "Couples", "Cultural"));

        listaPublicaciones.add(new Publicacion("Isla Contoy National Park",
                "Located in Quintana Roo",
                "Isla Contoy is a stunning uninhabited island and national park known for its biodiversity and pristine beaches. This eco-tourism destination is perfect for nature lovers and those seeking a peaceful getaway.",
                "Visitors often find the natural beauty and tranquility of the island captivating. It’s an excellent choice for families and solo travelers looking to escape the crowds and connect with nature.",
                R.drawable.isla_contoy,
                "$1,000 MX - $2,500 MX", "Solo traveler", "Adventure"));





        adapter = new PublicacionAdapter(listaPublicaciones);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fabProfile = findViewById(R.id.fabProfile);
        fabProfile.setOnClickListener(view -> {
            // Abrir la actividad de perfil
            Intent intent = new Intent(Catalogo.this, Perfil.class);
            startActivity(intent);
        });
    }


    public void abrirFiltro(View v) {
        Intent i = new Intent(this, Filtro.class);
        startActivityForResult(i, FILTER_REQUEST_CODE); // Cambia a startActivityForResult
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String presupuesto = data.getStringExtra("BUDGET");
            String tamañoGrupo = data.getStringExtra("GROUP_SIZE");
            String tipoLugar = data.getStringExtra("TYPE_OF");

            aplicarFiltros(presupuesto, tamañoGrupo, tipoLugar);
        }
    }

    private void aplicarFiltros(String presupuesto, String tamañoGrupo, String tipoLugar) {

        List<Publicacion> listaFiltrada = new ArrayList<>();
        for (Publicacion publicacion : listaPublicaciones) {
            boolean matches = true;
            // Comprobaciones de filtros
            if (!publicacion.getPresupuesto().trim().equals(presupuesto.trim())) {

                matches = false;
            }
            if (!publicacion.getTamañoGrupo().trim().equals(tamañoGrupo.trim()) ) {
                matches = false;
            }
            if (!publicacion.getTipoLugar().trim().equals(tipoLugar.trim()) ) {

                matches = false;
            }

            if (matches) {
                listaFiltrada.add(publicacion);
            }
        }

        // Actualiza el adaptador con la lista filtrada
        adapter = new PublicacionAdapter(listaFiltrada);
        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Filtros aplicados", Toast.LENGTH_SHORT).show();
    }





}
