// Noi chua goi cac API tu BE
const API = 'http://localhost:8080/api/category-management'

// API => Xu ly bat dong bo: async & await
// GET
export const getCategories = async () => {
  const res = await fetch(`${API}/playlist`)
  if (!res.ok) throw new Error('Fail roi')
  return await res.json()
}

export const getCategoryById = async (id) => {
  const res = await fetch(`${API}/detail/${id}`)
  if (!res.ok) throw new Error('Fail roi')
  return await res.json()
}

export const deleteCategory = async (id) => {
  const res = await fetch(`${API}/delete/${id}`, {
    method: DELETE,
  })
  if (!res.ok) {
    const errors = await res.text()
    throw new Error('Fail roi' + errors)
  }
}

export const addCategory = async (data) => {
  const res = await fetch(`${API}/add`, {
    method: POST,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data), // Chinh la RequestBody => duoi dinh dang Json
  })
  return await res.json()
}

export const updateCategory = async (data, id) => {
  const res = await fetch(`${API}/update/${id}`, {
    method: PUT,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data), // Chinh la RequestBody => duoi dinh dang Json
  })
  return await res.json()
}
