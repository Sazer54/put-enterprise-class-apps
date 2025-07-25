﻿using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace CompanyApi.Models;

public partial class CompanyDbContext : DbContext
{
    public CompanyDbContext()
    {
    }

    public CompanyDbContext(DbContextOptions<CompanyDbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Department> Departments { get; set; }

    public virtual DbSet<Employee> Employees { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Department>(entity =>
        {
            entity.Property(e => e.Name).UseCollation("SQL_Latin1_General_CP1_CI_AS");
        });

        modelBuilder.Entity<Employee>(entity =>
        {
            entity.Property(e => e.FirstName).UseCollation("SQL_Latin1_General_CP1_CI_AS");
            entity.Property(e => e.LastName).UseCollation("SQL_Latin1_General_CP1_CI_AS");

            entity.HasOne(d => d.Department).WithMany(p => p.Employees).HasConstraintName("FK_Employee_Department");

            entity.HasOne(d => d.Manager).WithMany(p => p.Subordinate).HasConstraintName("FK_Employee_Employee");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
