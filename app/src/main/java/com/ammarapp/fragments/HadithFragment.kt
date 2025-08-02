package com.ammarapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ammarapp.adapters.HadithAdapter
import com.ammarapp.databinding.FragmentHadithBinding
import com.ammarapp.models.Hadith

class HadithFragment : Fragment() {
    
    private var _binding: FragmentHadithBinding? = null
    private val binding get() = _binding!!
    private lateinit var hadithAdapter: HadithAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHadithBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        loadHadiths()
    }
    
    private fun setupUI() {
        binding.recyclerViewHadith.layoutManager = LinearLayoutManager(context)
        hadithAdapter = HadithAdapter { hadith ->
            openHadith(hadith)
        }
        binding.recyclerViewHadith.adapter = hadithAdapter
        
        binding.searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                filterHadiths(newText)
                return true
            }
        })
        
        binding.buttonRandom.setOnClickListener {
            showRandomHadith()
        }
        
        binding.buttonFavorites.setOnClickListener {
            showFavorites()
        }
    }
    
    private fun loadHadiths() {
        val hadiths = listOf(
            Hadith(
                id = 1,
                title = "حديث الإيمان",
                arabicText = "الإيمان بضع وسبعون شعبة، أعلاها قول لا إله إلا الله، وأدناها إماطة الأذى عن الطريق، والحياء شعبة من الإيمان",
                translation = "الإيمان بضع وسبعون شعبة، أعلاها قول لا إله إلا الله، وأدناها إماطة الأذى عن الطريق، والحياء شعبة من الإيمان",
                narrator = "أبو هريرة",
                source = "صحيح البخاري",
                isFavorite = false
            ),
            Hadith(
                id = 2,
                title = "حديث النية",
                arabicText = "إنما الأعمال بالنيات، وإنما لكل امرئ ما نوى",
                translation = "إنما الأعمال بالنيات، وإنما لكل امرئ ما نوى",
                narrator = "عمر بن الخطاب",
                source = "صحيح البخاري",
                isFavorite = false
            ),
            Hadith(
                id = 3,
                title = "حديث الأخوة",
                arabicText = "لا يؤمن أحدكم حتى يحب لأخيه ما يحب لنفسه",
                translation = "لا يؤمن أحدكم حتى يحب لأخيه ما يحب لنفسه",
                narrator = "أنس بن مالك",
                source = "صحيح البخاري",
                isFavorite = false
            ),
            Hadith(
                id = 4,
                title = "حديث الصدقة",
                arabicText = "الصدقة تطفئ الخطيئة كما يطفئ الماء النار",
                translation = "الصدقة تطفئ الخطيئة كما يطفئ الماء النار",
                narrator = "عبد الله بن مسعود",
                source = "سنن الترمذي",
                isFavorite = false
            ),
            Hadith(
                id = 5,
                title = "حديث العلم",
                arabicText = "طلب العلم فريضة على كل مسلم",
                translation = "طلب العلم فريضة على كل مسلم",
                narrator = "أنس بن مالك",
                source = "سنن ابن ماجه",
                isFavorite = false
            ),
            Hadith(
                id = 6,
                title = "حديث الصلاة",
                arabicText = "الصلاة عماد الدين، من أقامها فقد أقام الدين، ومن هدمها فقد هدم الدين",
                translation = "الصلاة عماد الدين، من أقامها فقد أقام الدين، ومن هدمها فقد هدم الدين",
                narrator = "عبد الله بن عمر",
                source = "صحيح البخاري",
                isFavorite = false
            ),
            Hadith(
                id = 7,
                title = "حديث الصبر",
                arabicText = "ما يصيب المسلم من نصب ولا وصب ولا هم ولا حزن ولا أذى ولا غم، حتى الشوكة يشاكها، إلا كفر الله بها من خطاياه",
                translation = "ما يصيب المسلم من نصب ولا وصب ولا هم ولا حزن ولا أذى ولا غم، حتى الشوكة يشاكها، إلا كفر الله بها من خطاياه",
                narrator = "أبو سعيد الخدري",
                source = "صحيح البخاري",
                isFavorite = false
            ),
            Hadith(
                id = 8,
                title = "حديث التوكل",
                arabicText = "لو أنكم توكلون على الله حق توكله لرزقكم كما يرزق الطير، تغدو خماصاً وتروح بطاناً",
                translation = "لو أنكم توكلون على الله حق توكله لرزقكم كما يرزق الطير، تغدو خماصاً وتروح بطاناً",
                narrator = "عمر بن الخطاب",
                source = "سنن الترمذي",
                isFavorite = false
            ),
            Hadith(
                id = 9,
                title = "حديث الدعاء",
                arabicText = "الدعاء هو العبادة",
                translation = "الدعاء هو العبادة",
                narrator = "النعمان بن بشير",
                source = "سنن الترمذي",
                isFavorite = false
            ),
            Hadith(
                id = 10,
                title = "حديث الرحمة",
                arabicText = "الراحمون يرحمهم الرحمن، ارحموا من في الأرض يرحمكم من في السماء",
                translation = "الراحمون يرحمهم الرحمن، ارحموا من في الأرض يرحمكم من في السماء",
                narrator = "عبد الرحمن بن عوف",
                source = "سنن الترمذي",
                isFavorite = false
            )
        )
        
        hadithAdapter.submitList(hadiths)
    }
    
    private fun filterHadiths(query: String?) {
        // تصفية الأحاديث حسب البحث
    }
    
    private fun openHadith(hadith: Hadith) {
        // فتح الحديث في شاشة منفصلة
    }
    
    private fun showRandomHadith() {
        // إظهار حديث عشوائي
    }
    
    private fun showFavorites() {
        // إظهار الأحاديث المفضلة
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}