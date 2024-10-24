import React from 'react';
import './AdminPage.scss';
import TechnologyEditMenu from '@/modules/TechnologyEditMenu/components/TechnologyEditMenu/TechnologyEditMenu';
import TechnologyCreateForm from '@/modules/TechnologyCreateForm/components/TechnologyCreateForm/TechnologyCreateForm';

const AdminPage = () => {
  return (
    <div className="admin-page">
      <TechnologyEditMenu />
      <TechnologyCreateForm />
    </div>
  );
};

export default AdminPage;
