package com.example.librarymanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.databinding.FragmentEmployeeBinding
import com.example.librarymanagementsystem.models.Employee

class EmployeeFragment : Fragment() {
    private var _binding: FragmentEmployeeBinding? = null
    private val binding get() = _binding!!

    private val employeeList = mutableListOf(   Employee("Nguyen Van A", "20"),
        Employee("Tran Thi B", "21"),
        Employee("Le Van C", "22"),
        Employee("Pham Thi D", "23"))

    private lateinit var adapter: EmployeeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Khởi tạo RecyclerView và Adapter
        adapter = EmployeeAdapter(employeeList)
        binding.rvEmployee.layoutManager = LinearLayoutManager(context)
        binding.rvEmployee.adapter = adapter

        // Thêm nhân viên khi nhấn nút "Thêm"
        binding.btnAdd.setOnClickListener {
            val name = "Nhân viên ${employeeList.size + 1} "
            val position = "${employeeList.size + 1}"

            // Thêm nhân viên mới vào danh sách
            employeeList.add(Employee(name, position))

            // Cập nhật adapter
            adapter.notifyItemInserted(employeeList.size - 1)
        }
    }
}