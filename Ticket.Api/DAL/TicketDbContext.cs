using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
using Ticket.Api.Models;

namespace Ticket.Api.DAL;

public partial class TicketDbContext : DbContext
{

    public TicketDbContext(DbContextOptions<TicketDbContext> options): base(options){}

    public virtual DbSet<Clientes> Clientes { get; set; }

    public virtual DbSet<Tickets> Tickets { get; set; }

    public virtual DbSet<Estatus> Estatus { get; set; }
    public virtual DbSet<Configuraciones> Configuraciones { get; set; }
    public virtual DbSet<Respuestas> Respuestas { get; set; }

    public virtual DbSet<Prioridades> Prioridades { get; set; }

    public virtual DbSet<Sistemas> Sistemas { get; set; }

    public virtual DbSet<Tipos> Tipos { get; set; }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Clientes>(entity =>
        {
            entity.HasKey(e => e.ClienteId).HasName("PK__Clientes__71ABD087B375C419");

            entity.Property(e => e.Nombres)
                .HasMaxLength(100)
                .IsUnicode(false);
        });

        modelBuilder.Entity<Tickets>(entity =>
        {
            entity.HasKey(e => e.TicketId).HasName("PK__eTicket__712CC6070A90FFD9");

            entity.ToTable("eTicket");

            entity.Property(e => e.Especificaciones).IsUnicode(false);
            entity.Property(e => e.FechaCreacion).HasColumnType("datetime");
            entity.Property(e => e.FechaFinalizado).HasColumnType("datetime");

            entity.HasOne(d => d.Cliente).WithMany(p => p.Tickets)
                .HasForeignKey(d => d.ClienteId)
                .HasConstraintName("FK__eTicket__Cliente__1CF15040");

            entity.HasOne(d => d.Estatus).WithMany(p => p.Tickets)
                .HasForeignKey(d => d.EstatusId)
                .HasConstraintName("FK__eTicket__Estatus__20C1E124");

            entity.HasOne(d => d.Prioridad).WithMany(p => p.Tickets)
                .HasForeignKey(d => d.PrioridadId)
                .HasConstraintName("FK__eTicket__Priorid__1FCDBCEB");

            entity.HasOne(d => d.Sistema).WithMany(p => p.Tickets)
                .HasForeignKey(d => d.SistemaId)
                .HasConstraintName("FK__eTicket__Sistema__1DE57479");

            entity.HasOne(d => d.Tipo).WithMany(p => p.Tickets)
                .HasForeignKey(d => d.TipoId)
                .HasConstraintName("FK__eTicket__TipoId__1ED998B2");
        });

        modelBuilder.Entity<Estatus>(entity =>
        {
            entity.HasKey(e => e.EstatusId).HasName("PK__Estatus__DE10F58DA375F6EC");

            entity.ToTable("Estatus");

            entity.Property(e => e.Estatus1)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Estatus");
        });

        modelBuilder.Entity<Prioridades>(entity =>
        {
            entity.HasKey(e => e.PrioridadId).HasName("PK__Priorida__3939172E0518F8F3");

            entity.Property(e => e.Prioridad)
                .HasMaxLength(50)
                .IsUnicode(false);
        });

        modelBuilder.Entity<Sistemas>(entity =>
        {
            entity.HasKey(e => e.SistemaId).HasName("PK__Sistemas__4C36BB868F17E7A9");

            entity.Property(e => e.Sistema)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Sistema");
        });

        modelBuilder.Entity<Tipos>(entity =>
        {
            entity.HasKey(e => e.TipoId).HasName("PK__Tipos__97099EB7224F755C");

            entity.Property(e => e.Tipo)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Tipo");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
