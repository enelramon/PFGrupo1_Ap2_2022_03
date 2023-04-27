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

    protected override void OnModelCreating(ModelBuilder builder)
    {
        builder.Entity<Clientes>(entity =>
        {
            entity.HasKey(e => e.ClienteId).HasName("PK__Clientes__71ABD087B375C419");

            entity.Property(e => e.Nombres)
                .HasMaxLength(100)
                .IsUnicode(false);
        });

        builder.Entity<Tickets>(entity =>
        {
            entity.HasKey(e => e.TicketId).HasName("PK__Tickets__712CC6070A90FFD9");

            entity.ToTable("Tickets");

            entity.Property(e => e.Especificaciones).IsUnicode(false);
            entity.Property(e => e.FechaCreacion).HasColumnType("datetime");
            entity.Property(e => e.FechaFinalizado).HasColumnType("datetime");
       
        });

        builder.Entity<Estatus>(entity =>
        {
            entity.HasKey(e => e.EstatusId).HasName("PK__Estatus__DE10F58DA375F6EC");

            entity.ToTable("Estatus");

            entity.Property(e => e.Estado)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Estatus");
        });

        builder.Entity<Prioridades>(entity =>
        {
            entity.HasKey(e => e.PrioridadId).HasName("PK__Priorida__3939172E0518F8F3");

            entity.Property(e => e.Prioridad)
                .HasMaxLength(50)
                .IsUnicode(false);
        });

        builder.Entity<Sistemas>(entity =>
        {
            entity.HasKey(e => e.SistemaId).HasName("PK__Sistemas__4C36BB868F17E7A9");

            entity.Property(e => e.Sistema)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Sistema");
        });

        builder.Entity<Tipos>(entity =>
        {
            entity.HasKey(e => e.TipoId).HasName("PK__Tipos__97099EB7224F755C");

            entity.Property(e => e.Tipo)
                .HasMaxLength(50)
                .IsUnicode(false)
                .HasColumnName("Tipo");
        });

        builder.Entity<Clientes>().HasData(new[]
        {
            new Clientes {ClienteId=1,ConfiguracionId=13,Clave="xtZTnA23",Nombres="Samuel"},
            new Clientes {ClienteId=2,ConfiguracionId=13,Clave="atDt23",Nombres="Rafa"},
            new Clientes {ClienteId=3,ConfiguracionId=13,Clave="mnQOJv23",Nombres="Jeison"}
        });
        builder.Entity<Estatus>().HasData(new[]
        {
            new Estatus {EstatusId=1,Estado = "Nuevo"},
            new Estatus {EstatusId=2,Estado = "En proceso"},
            new Estatus {EstatusId=3,Estado = "Cerrado"},
        });
        builder.Entity<Prioridades>().HasData(new[]
        {
            new Prioridades {PrioridadId=1, Prioridad="Baja"} ,
            new Prioridades {PrioridadId=2, Prioridad="Normal"} ,
            new Prioridades {PrioridadId=3, Prioridad="Media"} ,
            new Prioridades {PrioridadId=4, Prioridad="Alta"} ,
            new Prioridades {PrioridadId=5, Prioridad="Emergencia"} ,
        });
        builder.Entity<Sistemas>().HasData(new[]
        {
            new Sistemas{SistemaId=1, Sistema="Auto System Gym"},
            new Sistemas{SistemaId=2, Sistema="Auto System Office"},
            new Sistemas{SistemaId=3, Sistema="Auto System Finanzas"},
            new Sistemas{SistemaId=4, Sistema="Auto System Ventas"},
            new Sistemas{SistemaId=5, Sistema="Auto System Produccion"},
            new Sistemas{SistemaId=6, Sistema="Auto System Analyzer"},
            new Sistemas{SistemaId=7, Sistema="Auto System Dealer"},
            new Sistemas{SistemaId=8, Sistema="Auto System Education"},
            new Sistemas{SistemaId=9, Sistema="Auto System Restaurant"},
        });
        builder.Entity<Tipos>().HasData(new[]
        {
            new Tipos {TipoId=1,Tipo="Agregar opcion nueva"},
            new Tipos {TipoId=2,Tipo="Soporte sobre opcion existente"},
            new Tipos {TipoId=3,Tipo="Instalacion de software"},
            new Tipos {TipoId=4,Tipo="Solicitud de visita"},
            new Tipos {TipoId=5,Tipo="Solicitud de entrenamiento"},
            new Tipos {TipoId=6,Tipo="Soporte de hardware"},
            new Tipos {TipoId=7,Tipo="Otros"}
        });
        builder.Entity<Configuraciones>().HasData(new[]
        {
            new Configuraciones {ConfiguracionId=1,Theme=0, ColorSchemeIndex=0},//Light & Purpura
            new Configuraciones {ConfiguracionId=2,Theme=0, ColorSchemeIndex=1},//Light & verde
            new Configuraciones {ConfiguracionId=3,Theme=0, ColorSchemeIndex=2},//Light & azul
            new Configuraciones {ConfiguracionId=4,Theme=0, ColorSchemeIndex=3},//Light & Amarillo
            new Configuraciones {ConfiguracionId=5,Theme=0, ColorSchemeIndex=4},//Light & naranja
            new Configuraciones {ConfiguracionId=6,Theme=0, ColorSchemeIndex=5},//Light & Rojo

            new Configuraciones {ConfiguracionId=7,Theme=1, ColorSchemeIndex=0}, //Dark & Purpura
            new Configuraciones {ConfiguracionId=8,Theme=1, ColorSchemeIndex=1}, //Dark & verde
            new Configuraciones {ConfiguracionId=9,Theme=1, ColorSchemeIndex=2}, //Dark & azul
            new Configuraciones {ConfiguracionId=10,Theme=1, ColorSchemeIndex=3},//Dark & amarillo
            new Configuraciones {ConfiguracionId=11,Theme=1, ColorSchemeIndex=4},//Dark & naranja
            new Configuraciones {ConfiguracionId=12,Theme=1, ColorSchemeIndex=5},//Dark & rojo

            new Configuraciones {ConfiguracionId=13,Theme=2, ColorSchemeIndex=0},//System & Purpura
            new Configuraciones {ConfiguracionId=14,Theme=2, ColorSchemeIndex=1},//System & verde
            new Configuraciones {ConfiguracionId=15,Theme=2, ColorSchemeIndex=2},//System & azul
            new Configuraciones {ConfiguracionId=16,Theme=2, ColorSchemeIndex=3},//System & amarillo
            new Configuraciones {ConfiguracionId=17,Theme=2, ColorSchemeIndex=4},//System & naranja
            new Configuraciones {ConfiguracionId=18,Theme=2, ColorSchemeIndex=5},//System & rojo
        });

        OnModelCreatingPartial(builder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
