import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import Page from './routes/Page'
import HomePage from './routes/Page/HomePage'
import AddressListPage from './routes/Page/AddressListPage'
import FormPage from './routes/Page/FormPage'

export default function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Page />}>
          <Route index element={<HomePage />} />
          <Route path="address-list" element={<AddressListPage />} />
          <Route path="form-page/:userId" element={<FormPage />} />
        </Route>
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  )
}

