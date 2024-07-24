package fragments


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.unit_3.sogong_test.BookmarkedNewsActivity
import com.unit_3.sogong_test.ChangeEmailActivity
import com.unit_3.sogong_test.ChangeNicknameActivity
import com.unit_3.sogong_test.ChangePasswordActivity
import com.unit_3.sogong_test.MapViewActivity
import com.unit_3.sogong_test.R
import com.unit_3.sogong_test.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page, container, false)

        // Bottom navigation click listeners
        binding.bottomNavigationLocal.setOnClickListener {
//            it.findNavController().navigate(R.id.action_myPageFragment_to_chatFragment)
            startActivity(Intent(context, MapViewActivity::class.java))
        }
        binding.bottomNavigationHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_homeFragment)
        }
        binding.bottomNavigationMyKeyword.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_myKeywordFragment)
        }
        binding.bottomNavigationFeed.setOnClickListener {
            it.findNavController().navigate(R.id.action_myPageFragment_to_feedFragment)
        }


        // Handle click on "내가 북마크한 글"
        binding.bookmarkedNewsTextView.setOnClickListener {
            openBookmarkedNewsActivity()
        }

        // 닉네임 변경 버튼 클릭 리스너 추가
        binding.buttonChangeNickname.setOnClickListener {
            val intent = Intent(requireContext(), ChangeNicknameActivity::class.java)
            startActivity(intent)
        }

        // 이메일 변경 버튼 클릭 리스너 추가
        binding.buttonChangeEmail.setOnClickListener {
            val intent = Intent(requireContext(), ChangeEmailActivity::class.java)
            startActivity(intent)
        }

        // 비밀번호 변경 버튼 클릭 리스너 추가
        binding.buttonChangePassword.setOnClickListener {
            val intent = Intent(requireContext(), ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        // SharedPreferences에서 닉네임 불러오기
        val sharedPreferences = requireContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val nickname = sharedPreferences.getString("nickname", "김나눔")
        val email = sharedPreferences.getString("email", "nanseulgim365@gmail.com")

        binding.nicknameTextView.text = nickname
        binding.emailTextView.text = email

        return binding.root
    }

    private fun openBookmarkedNewsActivity() {
        val intent = Intent(requireContext(), BookmarkedNewsActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        // 닉네임과 이메일이 변경되었을 경우를 대비해 onResume에서 업데이트
        val sharedPreferences = requireContext().getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val nickname = sharedPreferences.getString("nickname", "김나눔")
        val email = sharedPreferences.getString("email", "nanseulgim365@gmail.com")
        binding.nicknameTextView.text = nickname
        binding.emailTextView.text = email
    }
}
